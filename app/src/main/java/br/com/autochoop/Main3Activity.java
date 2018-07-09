package br.com.autochoop;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main3Activity extends AppCompatActivity {
//    private static UsbSerialPort sPort = null;
//    private final String TAG = MainActivity.class.getSimpleName();
//    private final ExecutorService mExecutor = Executors.newSingleThreadExecutor();
//    private SerialInputOutputManager mSerialIoManager;
//
//    private final SerialInputOutputManager.Listener mListener =
//            new SerialInputOutputManager.Listener() {
//
//                @Override
//                public void onRunError(Exception e) {
//                    Log.d(TAG, "Runner stopped.");
//                }
//
//                @Override
//                public void onNewData(final byte[] data) {
//                    Main3Activity.this.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Main3Activity.this.updateReceivedData(data);
//                        }
//                    });
//                }
//            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

    }
    private void stopIoManager() {
     /*   if (mSerialIoManager != null) {
            Log.i(TAG, "Stopping io manager ..");
            mSerialIoManager.stop();
            //  mSerialIoManager = null;
        }*/
    }

    private void startIoManager() {
       /* if (sPort != null) {
            Log.i(TAG, "Starting io manager ..");
            mSerialIoManager = new SerialInputOutputManager(sPort, mListener);
            mExecutor.submit(mSerialIoManager);
        }*/
    }
    private void onDeviceStateChange() {
        stopIoManager();
        startIoManager();
    }

    private void updateReceivedData(byte[] data) {
     //   String value = converString(HexDump.dumpHexString(data));


    }
    private String converString(String value){
        String[] newValue = value.split("|");
        System.out.println("valor"+ newValue[1]);
        return  newValue[1];
    }


}
