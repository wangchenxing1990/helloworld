package com.technology.waangyukui.observer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.technology.waangyukui.mycyclerapp.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by lenvo on 2018/6/7.
 */

public class ObserverActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_jump_two;
    private Button button_send;
    private Button button_start_three;
    private TextView text_view;
    private TextView text_view_two;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observer);
        //注册订阅者
        EventBus.getDefault().register(this);

        button_jump_two = findViewById(R.id.button_jump_two);
        button_send = findViewById(R.id.button_send);
        button_start_three = findViewById(R.id.button_start_three);
        text_view = findViewById(R.id.text_view);
        text_view_two = findViewById(R.id.text_view_two);

        button_jump_two.setOnClickListener(this);
        button_send.setOnClickListener(this);
        button_start_three.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_jump_two:
                Intent intent = new Intent(getApplicationContext(), TwoObserverActivity.class);
                startActivity(intent);
                break;
            case R.id.button_send:
                EventBus.getDefault().postSticky(new MessageEvent("粘性事件0", "十年磨一剑霜刃未曾试"));
                EventBus.getDefault().postSticky(new MessageEventTwo("粘性事件1", "直挂云帆济沧海长风破浪会有时"));
                EventBus.getDefault().postSticky("玲珑骰子安红豆入骨相思知不知");
                Intent intentTwoObserverActivity = new Intent(getApplicationContext(), TwoObserverActivity.class);
                startActivity(intentTwoObserverActivity);
                break;
            case R.id.button_start_three:
                Intent intentThree = new Intent(getApplicationContext(), ThreeObserverActivity.class);
                startActivity(intentThree);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void userEventBus(UserEvent userEvent) {
        text_view.setText(userEvent.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEvent(MessageEvent messageEvent) {
        text_view_two.setText(messageEvent.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
