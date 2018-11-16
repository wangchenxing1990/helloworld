package com.technology.waangyukui.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by lenvo on 2018/6/11.
 */

public class MyReceiver extends BroadcastReceiver {
    ServiceActivity serviceActivity;
    int progresss;
    @Override
    public void onReceive(Context context, Intent intent) {
        progresss = intent.getIntExtra("progress", 0);
        Log.i("progress:::",progresss+"");
    }

    public void setTwoProgressBar(ProgressBar progressBar) {

        progressBar.setProgress(progresss);
    }
}
