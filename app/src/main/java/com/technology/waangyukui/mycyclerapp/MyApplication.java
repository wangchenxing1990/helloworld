package com.technology.waangyukui.mycyclerapp;

import android.app.Application;
import android.content.Context;

import com.haoge.easyandroid.EasyAndroid;

/**
 * Created by lenvo on 2018/6/7.
 */
public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context=getApplicationContext();
        EasyAndroid.init(context);
    }
}
