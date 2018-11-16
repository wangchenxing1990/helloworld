package com.technology.waangyukui.suspendcart;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.technology.waangyukui.mycyclerapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenvo on 2018/5/24.
 */

public class SuspendCarActivity extends AppCompatActivity {
    private ListView list_suspend_car;
    private ImageView iv_cart;
    private List<String> titles=new ArrayList<>();
    int moveDistance;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspend_car);
        list_suspend_car=findViewById(R.id.list_suspend_car);
        iv_cart=findViewById(R.id.iv_cart);

        initData();
        list_suspend_car.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,titles));

        initImageView();
    }

    private void initImageView() {
        iv_cart.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                moveDistance=getDisplayMetrics()[0]-iv_cart.getRight()+iv_cart.getWidth()/2;
                Log.i("right",moveDistance+"");
                iv_cart.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 60; i++) {
            titles.add(new StringBuffer("这是一条数据").append(i).toString());
        }
    }

    private int[] getDisplayMetrics(){
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int w=displayMetrics.widthPixels;
        int h=displayMetrics.heightPixels;
        int[] array={w,h};
        return array;
    }
    int startY;
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY= (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(startY-ev.getY())>10){
                    if (isShowFloatImage){
                        hideFloatImage(moveDistance);
                    }
                }
                startY= (int) ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(final Message message) {
                       if (message.what==0){
                           runOnUiThread(new Runnable() {
                               @Override
                               public void run() {
                                   if (!isShowFloatImage){
                                       showFloatImage(moveDistance);
                                   }
                               }
                           });
                       }
                        return true;
                    }
                }).sendEmptyMessageDelayed(0,25000);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
private boolean isShowFloatImage=true;
    private void showFloatImage(int distance){
        isShowFloatImage=false;
        TranslateAnimation translateAnimation=new TranslateAnimation(distance,0,0,0);
        translateAnimation.setDuration(300);

        AlphaAnimation alphaAnimation=new AlphaAnimation(0.5f,1f);
        alphaAnimation.setDuration(300);

        AnimationSet animationSet=new AnimationSet(true);
        alphaAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        iv_cart.startAnimation(animationSet);
    }

    private void hideFloatImage(int distance){
        isShowFloatImage=true;
        TranslateAnimation translateAnimation=new TranslateAnimation(distance,0,0,0);
        translateAnimation.setDuration(300);

        AlphaAnimation alphaAnimation=new AlphaAnimation(1f,0.5f);
        alphaAnimation.setDuration(300);

        AnimationSet animationSet=new AnimationSet(true);
        alphaAnimation.setFillAfter(true);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        iv_cart.startAnimation(animationSet);
    }

}
