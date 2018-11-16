package com.technology.waangyukui.toast;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/6/6.
 */

public class ToastActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonSimpleText;
    private Button buttonLongText;
    private Button buttonRightIcon;
    private Button buttonErrorIcon;
    private Button buttonRightIconLong;
    private Button buttonErrorIconLong;
    private ImageView image_add;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        buttonSimpleText = findViewById(R.id.button_simple_text);
        buttonLongText = findViewById(R.id.button_long_text);
        buttonRightIcon = findViewById(R.id.button_right_icon);
        buttonErrorIcon = findViewById(R.id.button_error_icon);
        buttonRightIconLong = findViewById(R.id.button_right_icon_long);
        buttonErrorIconLong = findViewById(R.id.button_error_icon_long);
        image_add=findViewById(R.id.image_add);
//        ObjectAnimator omt=ObjectAnimator.ofFloat(image_add, "rotationY", 0,360);
//        omt.setDuration(2000);
//        omt.setInterpolator(new AccelerateInterpolator());
//        omt.setRepeatCount(3);
//        omt.start();

        Rotato3dAnimation myYAnimation = new Rotato3dAnimation();
        myYAnimation.setRepeatCount(Animation.INFINITE); //旋转的次数（无数次）
        image_add.startAnimation(myYAnimation);

        buttonSimpleText.setOnClickListener(this);
        buttonLongText.setOnClickListener(this);
        buttonRightIcon.setOnClickListener(this);
        buttonErrorIcon.setOnClickListener(this);
        buttonRightIconLong.setOnClickListener(this);
        buttonErrorIconLong.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_simple_text:
                MyToast.showToast(getApplicationContext(),"显示的是一个纯文本的吐司");
                break;
            case R.id.button_long_text:
                MyToast.showToast(getApplicationContext(),"显示的是一个有时间的吐司", Toast.LENGTH_SHORT);
                break;
            case R.id.button_right_icon:
                MyToast.showToast(getApplicationContext(),"显示的是一个显示正确图片的吐司", true);
                break;
            case R.id.button_error_icon:
                MyToast.showToast(getApplicationContext(),"显示的是一个显示错误图片的吐司", false);
                break;
            case R.id.button_right_icon_long:
                MyToast.showToast(getApplicationContext(),"显示的是一个显示正确图片有时间的吐司", Toast.LENGTH_SHORT,true);
                break;
            case R.id.button_error_icon_long:

                MyToast.showToast(getApplicationContext(),"显示的是一个显示错误图片有时间的吐司", Toast.LENGTH_SHORT,false);
                break;

        }
    }
}
