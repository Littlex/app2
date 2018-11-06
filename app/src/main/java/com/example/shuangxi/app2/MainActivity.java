package com.example.shuangxi.app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button RP;
    private static final String TOAST_INTENT_SF =
            "com.example.shuangxi.app1.SF";
    private static final String TOAST_INTENT_NY =
            "com.example.shuangxi.app1.NY";
    private static final String SXZHU_PERMISSION =
            "edu.uic.cs478.f18.project3" ;
    private BroadcastReceiver receiver_SF;
    private BroadcastReceiver receiver_NY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RP = (Button) findViewById(R.id.button);
        RP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();    // check the permission
            }
        });

        //define  San Francisco broadcast receivers programmatically
        IntentFilter filter_SF = new IntentFilter();
        filter_SF.addAction(TOAST_INTENT_SF);
        filter_SF.setPriority(2);  // set filter priority so that app2 that receiver in A2 is always executed before the
        // receiver in A3, after A1 sends a broadcast.
        receiver_SF = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "San Francisco!  --From App2",
                        Toast.LENGTH_LONG).show() ;
            }
        };
        registerReceiver(receiver_SF, filter_SF, SXZHU_PERMISSION, null);



        //define  New York broadcast receivers programmatically
        IntentFilter filter_NY = new IntentFilter();
        filter_NY.addAction(TOAST_INTENT_NY);
        filter_NY.setPriority(2);  // set filter priority so that app2 that receiver in A2 is always executed before the
        // receiver in A3, after A1 sends a broadcast.
        receiver_NY = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "New York! --From App2",
                        Toast.LENGTH_LONG).show() ;
            }
        };
        registerReceiver(receiver_NY, filter_NY, SXZHU_PERMISSION, null);


    }

    // function for check permission and send Broadcast
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, SXZHU_PERMISSION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{SXZHU_PERMISSION}, 0);
        }
    }

}
