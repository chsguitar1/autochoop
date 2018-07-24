package br.com.autochoop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import br.com.autochoop.model.Cards;
import br.com.autochoop.model.Machine;
import br.com.autochoop.model.Products;
import br.com.autochoop.model.Sale;
import br.com.autochoop.model.Telemetria;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {



    @BindView(R.id.tv_product)
    TextView tvProduct;
    @BindView(R.id.tv_coin)
    TextView tvCoin;
    @BindView(R.id.tv_porlitro)
    TextView tvPorlitro;
    @BindView(R.id.tv_price_product)
    TextView tvPriceProduct;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.tv_coin2)
    TextView tvCoin2;
    /* @BindView(R.id.textView7)
     TextView textView7;*/
    @BindView(R.id.tv_value_buy)
    TextView tvValueBuy;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.tv_value_buy2)
    TextView tvValueBuy2;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("autobeer");
    boolean operationSale = false;
    Sale sale ;
    Machine machine;
    Double totValue = 0.0;
    Double valueCreditCard ;


  /*  private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
                    UsbAccessory accessory = manager.getAccessoryList()[0];
                    if (intent.getBooleanExtra(
                            UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        openAccessory(accessory);
                    } else {
                        Log.d(TAG, "permission denied for accessory "
                                + accessory);
                    }
                    mPermissionRequestPending = false;
                }
            } else if (UsbManager.ACTION_USB_ACCESSORY_DETACHED.equals(action)) {
                UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
                UsbAccessory accessory = manager.getAccessoryList()[0];
                if (accessory != null && accessory.equals(mAccessory)) {
                    closeAccessory();
                }
            }
        }
    };*/

    private Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        //mAdkManager = new AdkManager(this);
   /*     mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);*/
      /*  IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        registerReceiver(mUsbReceiver, filter);

        if (getLastNonConfigurationInstance() != null) {
            mAccessory = (UsbAccessory) getLastNonConfigurationInstance();
            openAccessory(mAccessory);
        }*/

        List<Telemetria> telemetrias = Telemetria.listAll(Telemetria.class);
        System.out.println("telemetrias" + telemetrias.size());
        if (telemetrias == null || telemetrias.size() == 0) {
            Telemetria tel = new Telemetria(new Date(), 0);
            tel.save();
        } else {
            Telemetria t = telemetrias.get(0);
            Products products = Select.from(Products.class)
                    .where(Condition.prop("idproduct")
                            .eq(t.getIdproduct())).list().get(0);
            if (products != null) {
                tvProduct.setText(products.getNameproduct());
                tvDescription.setText(products.getDescription());
                tvPriceProduct.setText(A.formatarValor(Double.parseDouble(products.getValueproduct())));
            }

        }


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stringValue = "100";
                mAdkManager.write(stringValue);

            }
        });*/
        //tvDescription.setText("Este estilo de cerveja é considerado, por mim e muitos, ideal para dias quentes pois é bastante refrescante e nutritivo. Tanto é que cervejas sem álcool, na Alemanha, são praticamente todas feitas a base de Weissbier sendo, cientificamente comprovado, mais eficientes do que os atuais energéticos");
        setupValues();
      //  setupCard();
    }
    void setupValues() {
        if(A.IDMACHINE != null) {
            myRef = database.getReference("autobeer");
            Query queryExtract = myRef.child("machines").orderByChild("idmachine").equalTo(A.IDMACHINE.getIdmachine());
            if (queryExtract != null) {

            }

            queryExtract.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    System.out.println("veio maquina " + dataSnapshot);

                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        machine = data.getValue(Machine.class);

                    }


                    if(operationSale && machine.isSale()){
                        calculateValues();
                    }else if( operationSale && !machine.isSale()){
                        finalSale();
                    }else if(!operationSale && machine.isSale()){
                        newSale();
                        calculateValues();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    A.showMsg(databaseError.getMessage() + "\n" + databaseError.getDetails(), MainActivity.this);
                }
            });



        }
    }
    void setupCard(){
if(A.IDMACHINE != null) {
    /*   // myRef = database.getReference("autobeer");
       ValueEventListener postvalue = new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               System.out.println(" datas "+ dataSnapshot );
              valueCreditCard = dataSnapshot.child("cards").child("84 88 68 1E").child("valuecredcard").getValue(Double.class);
               System.out.println("machine"+valueCreditCard);
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       };
       myRef.addValueEventListener(postvalue);*/
    myRef = database.getReference("autobeer");
    Query queryExtract = myRef.child("cards").child(A.IDMACHINE.getIdcard());
    if (queryExtract != null) {

    }
    queryExtract.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            System.out.println(" datas " + dataSnapshot);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
}
    }
    private void newSale() {
     //  setupCard();
        System.out.println("machine"+valueCreditCard);
        operationSale = true;
       sale =  new Sale();
        sale.setMachine(machine);
        sale.setStatus(1);
        sale.setIdcard(A.IDMACHINE.getIdcard());
        sale.setDateSale(new Timestamp(new Date().getTime()));
        sale.setValue(0.0);
    }

    private void finalSale() {
        operationSale = false;
        sale.setStatus(0);
        sale.setValue(totValue);
        myRef.child("machines").child(A.IDMACHINE.getIdmachine()).child("sale").setValue(false);
        myRef.child("machines").child(A.IDMACHINE.getIdmachine()).child("extractvalue").setValue(0.0);
        myRef.child("cards").child(A.IDMACHINE.getIdEmploy()).child("valuecredcard").setValue(this.machine.getCards().getValuecredcard() - totValue);
        myRef.child("sale").setValue(sale);


    }

    private void calculateValues() {
        if(valueCreditCard >= totValue){
            Double productValue = Double.parseDouble(machine.getProducts().getValueproduct().replace(".","").replace(",","."));
            Double extractValue = machine.getExtactvalue() / 1000;
            totValue = extractValue * productValue;
            tvValueBuy2.setText(A.formatarValor(machine.getCards().getValuecredcard() - totValue));
            tvValueBuy.setText(A.formatarValor(totValue));

        }else{
            A.showMsg("Cŕedito expirado \n recarrege oi cartão", MainActivity.this);
        }

        //  Double balance =  Double.parseDouble(tvValueBuy.getText().toString());


    }


    @Override
    protected void onResume() {
        super.onResume();
        //
        /*if (mInputStream != null && mOutputStream != null) {

            return;
        }
        UsbAccessory[] accessories = mUsbManager.getAccessoryList();
        UsbAccessory accessory = (accessories == null ? null : accessories[0]);
        if (accessory != null) {
            if (mUsbManager.hasPermission(accessory)) {
                openAccessory(accessory);
                //mAdkManager.open();
            } else {
                synchronized (mUsbReceiver) {
                    if (!mPermissionRequestPending) {
                        mUsbManager.requestPermission(accessory, mPermissionIntent);
                        mPermissionRequestPending = true;
                    }
                }
            }
        } else {
            Log.d(TAG, "mAccessory is null");
        }*/
    }

    @Override
    public void onDestroy() {
        //  unregisterReceiver(mUsbReceiver);
        super.onDestroy();

    }

    @Override
    protected void onPause() {
        super.onPause();
        // closeAccessory();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("salva").setIcon(R.drawable.ic_settings_white_24dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        this.menu = menu;
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getTitle().equals("salva")) {
            authAdmin();

            return true;
        }
        return false;
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Configurar").setIcon(R.drawable.ic_settings_white_24dp);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            *//*if(item.getItemId() == android.R.id.home) {
                onBackPressed();
            }else if (item.getTitle().equals("fechar")) {
                checkItensOrder(order);
            }else if(item.getItemId() == R.id.action_finish){
                new SincronizationREST().logoutUser(this,MainActivity.USERLOGIN.getLoginusu());
            }
            return  super.onOptionsItemSelected(item);
        }*//*
        authAdmin();

        return true;
    }*/

    @Override
    public void onBackPressed() {

    }

    void authAdmin() {
        new LovelyTextInputDialog(this)
                // .setTopColorRes(R.color.green_900)
                .setMessage("Digite sua senha!")
                .setIconTintColor(R.color.red_900)
                .setInputType(InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD)
                .setTitle(R.string.app_name)
                .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String text) {
                        if (text.equals("admin")) {
                            goSetup();

                        }
                    }
                })

                .show();
    }

    void goSetup() {
        Intent it = new Intent(MainActivity.this, SettingEmployActivity.class);
        startActivity(it);
    }

   /* @Override
    public Object onRetainCustomNonConfigurationInstance() {
       *//* if (mAccessory != null) {
            return mAccessory;
        } else {
            return super.onRetainNonConfigurationInstance();
        }*//*
    }*/

    /*private void openAccessory(UsbAccessory accessory) {
        mFileDescriptor = mUsbManager.openAccessory(accessory);
        if (mFileDescriptor != null) {
            mAccessory = accessory;
            FileDescriptor fd = mFileDescriptor.getFileDescriptor();
            mInputStream = new FileInputStream(fd);
            mOutputStream = new FileOutputStream(fd);
//            mAdkManager.open();
            Log.d(TAG, "accessory opened");
        } else {
            Log.d(TAG, "accessory open fail");
        }
    }*/


   /* private void closeAccessory() {
        try {
            if (mFileDescriptor != null) {
                mFileDescriptor.close();
            }
        } catch (IOException e) {
        } finally {
            mFileDescriptor = null;
            mAccessory = null;
        }
    }*/



    /*public void onClickStart() {

        HashMap<String, UsbDevice> usbDevices = usbManager.getDeviceList();
        if (!usbDevices.isEmpty()) {
            boolean keep = true;
            for (Map.Entry<String, UsbDevice> entry : usbDevices.entrySet()) {
                device = entry.getValue();
                int deviceVID = device.getVendorId();
                if (deviceVID == 0x2341)//Arduino Vendor ID
                {
                    PendingIntent pi = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
                    usbManager.requestPermission(device, pi);
                    keep = false;
                } else {
                    connection = null;
                    device = null;
                }

                if (!keep)
                    break;
            }
        }


    }*/

   /* public void onClickSend(View view) {
        //  String string = editText.getText().toString();
        String string = "50.0";
        serialPort.write(string.getBytes());
        //  tvAppend(textView, "\nData Sent : " + string.getBytes().toString() + "\n");
        System.out.println("PARA STRING" + string.getBytes().toString());
        serialPort.read(mCallback);

    }

    public void onClickStop() {
        //setUiEnabled(false);
        serialPort.close();
        //  tvAppend(textView,"\nSerial Connection Closed! \n");

    }*/

    public void onClickClear(View view) {
        //textView.setText(" ");
    }

    private void tvAppend(TextView tv, CharSequence text) {
        final TextView ftv = tv;
        final CharSequence ftext = text;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ftv.append(ftext);


            }
        });
    }

    public void blinkLED(View v) {

        byte[] buffer = new byte[1];


        buffer[0] = (byte) 0; // button says on, light is off

        buffer[0] = (byte) 1; // button says off, light is on

       /* if (mOutputStream != null) {
            try {
                mOutputStream.write(10000);

            } catch (IOException e) {
                Log.e(TAG, "write failed", e);
            }
        }*/
    }

    public void writeMsg(View v) {

        byte[] buffer = new byte[1];


        buffer[0] = (byte) 0; // button says on, light is off

        buffer[0] = (byte) 1; // button says off, light is on

        /*if (mInputStream != null) {
            try {
                mInputStream.read();
                System.out.println();

            } catch (IOException e) {
                Log.e(TAG, "write failed", e);
            }
        }*/
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // String t = (ValueMsg) msg.obj;

            // this is where you handle the data you sent. You get it by calling
            // the getReading() function
            //   tvCedito.setText("Flag: " + msg.toString());
        }
    };

   /* private void setupAccessory() {
        mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(
                ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        registerReceiver(mUsbReceiver, filter);
        if (getLastNonConfigurationInstance() != null) {
            mAccessory = (UsbAccessory) getLastNonConfigurationInstance();
            openAccessory(mAccessory);
        }
    }*/

   /* Runnable run = new Runnable() {
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {

                runOnUiThread(new Runnable() {
                    public void run() {
                   //     AdkMessage msg = mAdkManager.read();
                        String buffer = msg.getString();
                        if (buffer.length() > 0) {
                            Log.e("recebe", buffer);

                        }
                    }
                });
            }


        }
    };*/

    /*public void run() {
       // AdkMessage msg = mAdkManager.read();

        if (!msg.isEmpty() && msg != null) {
            Log.e("arduino", msg.getString());
            // tvCedito.setText(msg.getString());
        }

    }*/


}

