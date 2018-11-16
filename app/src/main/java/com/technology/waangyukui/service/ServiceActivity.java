package com.technology.waangyukui.service;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/6/11.
 */

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener,OnProgressListeners {
    private ProgressBar progressBar;
    private Button button;
    private MyService mService;
    private MyReceiver myReceiver;
    Intent intent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.wang.zhe.xuan.MyReceivers");
        registerReceiver(myReceiver, intentFilter);

//        intent = new Intent(getApplicationContext(), MyService.class);
//        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        button = findViewById(R.id.button_start);
        progressBar = findViewById(R.id.progress_bar_one);

        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
//       mService.startDownLoad();
        intent = new Intent(getApplicationContext(), MyService.class);
        startService(intent);
        myReceiver.setTwoProgressBar(progressBar);
//       bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mService = ((MyService.MsgBinder) iBinder).getService();
            mService.setOnProgressListener(new MyService.OnProgressListener() {
                @Override
                public void onProgress(int progress) {
                    progressBar.setProgress(progress);
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };


//    public class MyReceivers extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            int progress = intent.getIntExtra("progress", 0);
//            Log.i("progress:::", progress + "");
//            progressBar.setProgress(progress);
//        }
//    }

    @Override
    protected void onDestroy() {
        stopService(intent);
        unregisterReceiver(myReceiver);
        super.onDestroy();
    }

    @Override
    public void onProgress(int progress) {
        progressBar.setProgress(progress);
    }
}
