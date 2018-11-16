package com.technology.waangyukui.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.technology.waangyukui.toast.MyToast;

/**
 * Created by lenvo on 2018/9/12.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String name = intent.getStringExtra("name");
        Log.i("name", name);
        MyToast.showToast(context, name);
    }
}
