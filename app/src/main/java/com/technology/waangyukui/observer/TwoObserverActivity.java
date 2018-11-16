package com.technology.waangyukui.observer;

import android.content.Intent;
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

public class TwoObserverActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_send_one;
    private Button button_two;
    private TextView text_view_two;
    private TextView text_view_three;
    private TextView text_view_four;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_obsever);
        button_send_one=findViewById(R.id.button_send_one);
        button_two=findViewById(R.id.button_two);
        button_two=findViewById(R.id.button_two);
        text_view_two=findViewById(R.id.text_view_two);
        text_view_three=findViewById(R.id.text_view_three);
        text_view_four=findViewById(R.id.text_view_four);
        button_send_one.setOnClickListener(this);
        button_two.setOnClickListener(this);

        EventBus.getDefault().register(TwoObserverActivity.this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_send_one:
                EventBus.getDefault().post(new UserEvent("wangyukui","19655555"));
                EventBus.getDefault().post(new MessageEvent("wangyukui","去年今日此门中，人面桃花相映红"));
                EventBus.getDefault().postSticky(new UserEvent("wangyukui","19655555"));
                EventBus.getDefault().postSticky(new MessageEvent("wangyukui","去年今日此门中，人面桃花相映红"));
                EventBus.getDefault().postSticky("String");
                finish();
                Intent intentThree = new Intent(getApplicationContext(), ThreeObserverActivity.class);
                startActivity(intentThree);

                break;
            case R.id.button_two:
//                EventBus.getDefault().register(TwoObserverActivity.this);
                break;
        }
    }
//,sticky = true
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void receiveEventBus(MessageEvent messageEvent){
        text_view_two.setText(messageEvent.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void receiveEventBusTwo(MessageEventTwo messageEventTwo){
        text_view_three.setText(messageEventTwo.toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void receiveEventBusThree(String messageEventThree){
        text_view_four.setText(messageEventThree);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(TwoObserverActivity.this);
    }
}
