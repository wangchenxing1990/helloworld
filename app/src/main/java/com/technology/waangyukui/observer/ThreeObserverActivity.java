package com.technology.waangyukui.observer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.technology.waangyukui.mycyclerapp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by lenvo on 2018/6/7.
 */

public class ThreeObserverActivity extends AppCompatActivity {
    private Button button_send_one;
    private Button button_two;
    private TextView text_view_two;
    private TextView text_view_three;
    private TextView text_view_four;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_obsever);
        text_view_two = findViewById(R.id.text_view_two);
        text_view_three = findViewById(R.id.text_view_three);
        EventBus.getDefault().register(ThreeObserverActivity.this);
    }


    //,sticky = true
    @Subscribe(threadMode = ThreadMode.MAIN,sticky=true)
    public void receiveEventBus(MessageEvent messageEvent) {
        text_view_two.setText(messageEvent.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void receiveEventBuss(UserEvent userEvent) {
        text_view_three.setText(userEvent.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(ThreeObserverActivity.this);
    }
}
