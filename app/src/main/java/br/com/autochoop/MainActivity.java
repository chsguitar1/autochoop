package br.com.autochoop;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.autochoop.model.Cards;
import br.com.autochoop.model.Employ;
import br.com.autochoop.model.Products;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.palazzetti.adktoolkit.AdkManager;
import me.palazzetti.adktoolkit.response.AdkMessage;


public class MainActivity extends AppCompatActivity  {

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.tv_cedito)
    TextView tvCedito;
    private AdkManager mAdkManager;

    private static final String TAG = "ArduinoAccessory";
    private UsbManager mUsbManager;
    private PendingIntent mPermissionIntent;
    private boolean mPermissionRequestPending;
    private static final String ACTION_USB_PERMISSION = "com.google.android.DemoKit.action.USB_PERMISSION";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("autobeer");
    UsbAccessory mAccessory;
    ParcelFileDescriptor mFileDescriptor;
    FileInputStream mInputStream;
    FileOutputStream mOutputStream;


    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
                    UsbAccessory accessory = manager.getAccessoryList()[0];
                    if (intent.getBooleanExtra(
                            UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        // openAccessory(accessory);
                        mAdkManager.open();
                        Thread thread = new Thread(run);
                        thread.start();

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
                    //closeAccessory();
                    mAdkManager.close();
                }
            }
        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAdkManager = new AdkManager(this);
        mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        mPermissionIntent = PendingIntent.getBroadcast(this, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter = new IntentFilter(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
        registerReceiver(mUsbReceiver, filter);

        if (getLastNonConfigurationInstance() != null) {
            mAccessory = (UsbAccessory) getLastNonConfigurationInstance();
            openAccessory(mAccessory);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stringValue = "100";
                mAdkManager.write(stringValue);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //
        if (mInputStream != null && mOutputStream != null) {

            return;
        }
        UsbAccessory[] accessories = mUsbManager.getAccessoryList();
        UsbAccessory accessory = (accessories == null ? null : accessories[0]);
        if (accessory != null) {
            if (mUsbManager.hasPermission(accessory)) {
                // openAccessory(accessory);
                mAdkManager.open();
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
        }
    }
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub

        try{
            if(mUsbReceiver!=null)
                unregisterReceiver(mUsbReceiver);
        }catch(Exception e)
        {

        }
        super.onDestroy();

    }
    @Override
    protected void onPause() {
        super.onPause();
        closeAccessory();
    }


    private void openAccessory(UsbAccessory accessory) {
        mFileDescriptor = mUsbManager.openAccessory(accessory);
        if (mFileDescriptor != null) {
            mAccessory = accessory;
            FileDescriptor fd = mFileDescriptor.getFileDescriptor();
            mInputStream = new FileInputStream(fd);
            mOutputStream = new FileOutputStream(fd);
            mAdkManager.open();
            Log.d(TAG, "accessory opened");
        } else {
            Log.d(TAG, "accessory open fail");
        }
    }


    private void closeAccessory() {
        try {
            if (mFileDescriptor != null) {
                mFileDescriptor.close();
            }
        } catch (IOException e) {
        } finally {
            mFileDescriptor = null;
            mAccessory = null;
        }
    }

    private void saveData() {

        Cards cards = new Cards((long) 1, "0152", 50.0);
        Cards card2 = new Cards((long) 2, "0153", 50.0);
        Cards card3 = new Cards((long) 3, "0154", 50.0);
        List<Cards> cardsList = new ArrayList<>();
        cardsList.add(cards);
        cardsList.add(card2);
        cardsList.add(card3);

        //  employ.setCardsEmploys(cardsEmploy);

        Products products = new Products((long) 1, "CHOOP WEIS", 7.0);
        List<Products> productsList = new ArrayList<>();
        productsList.add(products);

        Employ employ = new Employ((long) 1, "teste de firebase", productsList, cardsList);
        myRef.child("empĺoy").child(String.valueOf(employ.getIdEmploy())).setValue(employ);

    }

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

        if (mOutputStream != null) {
            try {
                mOutputStream.write(10000);

            } catch (IOException e) {
                Log.e(TAG, "write failed", e);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("fechar").setIcon(R.mipmap.ic_launcher).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            /*if(item.getItemId() == android.R.id.home) {
                onBackPressed();
            }else if (item.getTitle().equals("fechar")) {
                checkItensOrder(order);
            }else if(item.getItemId() == R.id.action_finish){
                new SincronizationREST().logoutUser(this,MainActivity.USERLOGIN.getLoginusu());
            }

            return  super.onOptionsItemSelected(item);
        }*/
        return true;
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // String t = (ValueMsg) msg.obj;

            // this is where you handle the data you sent. You get it by calling
            // the getReading() function
            tvCedito.setText("Flag: " + msg.toString());
        }
    };

    private void setupAccessory() {
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
    }
    Runnable run = new Runnable() {
        public void run() {
            while (!Thread.currentThread ().isInterrupted()) {

                        runOnUiThread(new Runnable() {
                            public void run() {
                                AdkMessage msg = mAdkManager.read();
                                String buffer = msg.getString();
                                if ( buffer.length() > 0 ) {
                                    Log.e("recebe", buffer);

                                }
                            }
                        });
                    }


        }
    };
    public void run() {
        AdkMessage msg = mAdkManager.read();

        if(!msg.isEmpty() && msg != null){
           Log.e("arduino", msg.getString());
          // tvCedito.setText(msg.getString());
        }

    }

}
