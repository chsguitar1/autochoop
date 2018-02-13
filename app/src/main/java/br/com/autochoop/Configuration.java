package br.com.autochoop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Configuration extends Activity implements OnClickListener {
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.config_main);
        /*Button btnSimple = (Button) findViewById(R.id.btnSimple);
        btnSimple.setOnClickListener(this);

        Button btnListener = (Button) findViewById(R.id.btnListener);
        btnListener.setOnClickListener(this);

        Button btnAutoConnect = (Button) findViewById(R.id.btnAutoConnect);
        btnAutoConnect.setOnClickListener(this);

        Button btnDeviceList = (Button) findViewById(R.id.btnDeviceList);
        btnDeviceList.setOnClickListener(this);

        Button btnTerminal = (Button) findViewById(R.id.btnTerminal);
        btnTerminal.setOnClickListener(this);
        }*/
}
public void onClick(View v) {
    int id = v.getId();
    Intent intent = null;


}}
