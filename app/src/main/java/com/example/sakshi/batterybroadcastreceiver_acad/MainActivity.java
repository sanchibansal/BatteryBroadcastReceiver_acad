package com.example.sakshi.batterybroadcastreceiver_acad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //declaring text view, progressbar and BroadcastReceiver
    private TextView mBatteryLevelText;
    private ProgressBar mBatteryLevelProgress;

    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //associating text view and Progressbar with their ids.
        mBatteryLevelText = (TextView) findViewById(R.id.textView);
        mBatteryLevelProgress = (ProgressBar) findViewById(R.id.progressBar);
        mReceiver = new MyReceiver();


    }

    @Override
    protected void onStart() {
        //registering receiver
        registerReceiver(mReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        super.onStart();
    }

    @Override
    protected void onStop() {
        //unregistering receiver
        unregisterReceiver(mReceiver);
        super.onStop();
    }


   private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //getting battery level of the device through BatteryManager
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            //setting battery level to Textview and progressbar
            mBatteryLevelText.setText("Battery Level: " + level);
            mBatteryLevelProgress.setProgress(level);
        }
    }
}