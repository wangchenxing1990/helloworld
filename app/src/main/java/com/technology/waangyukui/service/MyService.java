package com.technology.waangyukui.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by lenvo on 2018/6/11.
 */

public class MyService extends Service {
    public static final int MAX_PROGRESS = 100;
    private int progree = 0;
//    private Intent intent=new Intent(getApplication(), ServiceActivity.MyReceiver.class);
    public int getProgress() {
        return progree;
    }

    public void startDownLoad() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                while (progree < MAX_PROGRESS) {
                    progree += 5;
//                    if (onProgressListener != null) {
//                        onProgressListener.onProgress(progree);
//                    }
                    Log.i("progress1111111:::",progree+"");
                    Intent intent=new Intent(getApplicationContext(), MyReceiver.class);
                    intent.setAction("com.wang.zhe.xuan.MyReceiver");
                    intent.putExtra("progress",progree);
                    sendBroadcast(intent);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startDownLoad();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MsgBinder();
    }

    class MsgBinder extends Binder {
        public MyService getService() {
            return new MyService();
        }
    }

    OnProgressListener onProgressListener;

    public void setOnProgressListener(OnProgressListener listener) {
        this.onProgressListener = listener;
    }

    public interface OnProgressListener {
        void onProgress(int progress);
    }

}
