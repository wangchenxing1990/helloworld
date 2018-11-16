package com.technology.waangyukui.broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/9/12.
 */

public class ReceiverActivity extends AppCompatActivity implements View.OnClickListener {
    MyBroadcastReceiver myBroadcastReceiver;
    private static final String BROADCAST_ACTION = "com.wangyukui";
    private Button send_broadcast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        send_broadcast = findViewById(R.id.send_broadcast);

        send_broadcast.setOnClickListener(this);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unregisterReceiver(myBroadcastReceiver);
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_broadcast:
                Intent intent = new Intent();
                intent.setAction(BROADCAST_ACTION);
                intent.putExtra("name", "阳光是明媚的\n空气是清新的\n生命是美好的\n我爱你是不变的");
                sendBroadcast(intent);
                break;
        }
    }
}
