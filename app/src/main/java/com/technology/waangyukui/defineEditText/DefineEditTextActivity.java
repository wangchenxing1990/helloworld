package com.technology.waangyukui.defineEditText;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/6/4.
 */

public class DefineEditTextActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView cardViewOne;
    private CardView cardViewTwo;
    private BottomTabLayout bottom_item_tab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_define_edit_text);
        cardViewOne=findViewById(R.id.card_view_one);
        cardViewTwo=findViewById(R.id.card_view_two);
        bottom_item_tab=findViewById(R.id.bottom_item_tab);
        cardViewOne.setOnClickListener(this);
        cardViewTwo.setOnClickListener(this);
        initBottomTab();
    }

    private BottomTabItem homeItemTab;
    private BottomTabItem discoveryItemTab;
    private BottomTabItem gameItemTab;
    private BottomTabItem moreItemTab;
    private void initBottomTab() {
        homeItemTab= (BottomTabItem) bottom_item_tab.getChildAt(0);
        discoveryItemTab= (BottomTabItem) bottom_item_tab.getChildAt(1);
        gameItemTab= (BottomTabItem) bottom_item_tab.getChildAt(2);
        moreItemTab= (BottomTabItem) bottom_item_tab.getChildAt(3);

        homeItemTab.setBackgroundResource(R.drawable.main_tab_background);
        discoveryItemTab.setBackgroundResource(R.drawable.main_tab_background);
        gameItemTab.setBackgroundResource(R.drawable.main_tab_background);
        moreItemTab.setBackgroundResource(R.drawable.main_tab_background);

        homeItemTab.setTextAndImage(R.drawable.home_tab_discovery,R.string.home);
        discoveryItemTab.setTextAndImage(R.drawable.home_tab_chat,R.string.discovery);
        gameItemTab.setTextAndImage(R.drawable.home_tab_contacts,R.string.find);
        moreItemTab.setTextAndImage(R.drawable.home_tab_me,R.string.more);

        homeItemTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemTab.setEnabled(view == homeItemTab ? false : true);
                discoveryItemTab.setEnabled(view == discoveryItemTab ? false : true);
                gameItemTab.setEnabled(view == gameItemTab ? false : true);
                moreItemTab.setEnabled(view == moreItemTab ? false : true);

                setSelectSwitchTab(0);

                Toast.makeText(getApplicationContext(),"点击的是首页按钮",Toast.LENGTH_SHORT).show();
            }
        });
        discoveryItemTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemTab.setEnabled(view == homeItemTab ? false : true);
                discoveryItemTab.setEnabled(view == discoveryItemTab ? false : true);
                gameItemTab.setEnabled(view == gameItemTab ? false : true);
                moreItemTab.setEnabled(view == moreItemTab ? false : true);

                setSelectSwitchTab(1);
                Toast.makeText(getApplicationContext(),"点击的是发现按钮",Toast.LENGTH_SHORT).show();
            }
        });
        gameItemTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemTab.setEnabled(view == homeItemTab ? false : true);
                discoveryItemTab.setEnabled(view == discoveryItemTab ? false : true);
                gameItemTab.setEnabled(view == gameItemTab ? false : true);
                moreItemTab.setEnabled(view == moreItemTab ? false : true);

                setSelectSwitchTab(2);
                Toast.makeText(getApplicationContext(),"点击的是发现按钮",Toast.LENGTH_SHORT).show();
            }
        });
        moreItemTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeItemTab.setEnabled(view == homeItemTab ? false : true);
                discoveryItemTab.setEnabled(view == discoveryItemTab ? false : true);
                gameItemTab.setEnabled(view == gameItemTab ? false : true);
                moreItemTab.setEnabled(view == moreItemTab ? false : true);

                setSelectSwitchTab(3);
                Toast.makeText(getApplicationContext(),"点击的是更多的按钮",Toast.LENGTH_SHORT).show();
            }
        });
       setSelectSwitchTab(0);
    }

    private void setSelectSwitchTab(int mode) {
        homeItemTab.setSelected(mode == 0 ? true : false);
        discoveryItemTab.setSelected(mode == 1 ? true : false);
        gameItemTab.setSelected(mode == 2 ? true : false);
        moreItemTab.setSelected(mode == 3 ? true : false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_view_one:
                Toast.makeText(getApplicationContext(),"点击的是第一个cardView",Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_view_two:
                Toast.makeText(getApplicationContext(),"点击的是第二个cardView",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
