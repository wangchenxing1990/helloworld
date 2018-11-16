package com.technology.waangyukui.toast;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/6/6.
 */

public class MyToast extends Toast {
    private static final int TYPE_HIDE = -1;
    private static final int TYPE_TRUE = 0;
    private static final int TYPE_FALSE = 1;
    private static MyToast toast;

    public MyToast(Context context) {
        super(context);
    }

    public static TextView toastText;
    public static ImageView toastImg;

    public static void initToast(Context context, CharSequence text) {
        toast = new MyToast(context);
        LayoutInflater linearLayout = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = linearLayout.inflate(R.layout.toast_layout, null);
        toastText = layout.findViewById(R.id.toast_text);
        toastImg = layout.findViewById(R.id.toast_img);

        toastText.setText(text);
        toast.setView(layout);
        toast.setGravity(Gravity.CENTER, 0, 70);
    }

    public static void showToast(Context context, CharSequence text, int time, int imgTYpe) {
        initToast(context, text);
        if (time == Toast.LENGTH_LONG) {
            toast.setDuration(Toast.LENGTH_LONG);
        } else {
            toast.setDuration(Toast.LENGTH_SHORT);
        }

        if (imgTYpe == TYPE_HIDE) {
            toastImg.setVisibility(View.GONE);
        } else {
            if (imgTYpe == TYPE_TRUE) {
                toastImg.setBackgroundResource(R.mipmap.finish_select);
            } else {
                toastImg.setBackgroundResource(R.mipmap.button_add);
            }
            toastImg.setVisibility(View.VISIBLE);

//            ObjectAnimator.ofFloat(toastImg, "rotationY", 0, 360).setDuration(2700).start();
            // 动画
            ObjectAnimator omt=ObjectAnimator.ofFloat(toastImg, "RotationY", 0f, 360f);
            omt.setDuration(1300);
            omt.setRepeatCount(3);
            omt.start();
        }

        toast.show();
    }

    /**
     * 显示一个纯文本
     * @param context
     * @param text
     */
    public static void showToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT, TYPE_HIDE);
    }

    /**
     * 显示一个有图标的吐司
     * @param context
     * @param text
     * @param isSucceed
     */
    public static  void showToast(Context context, CharSequence text, boolean isSucceed) {
        showToast(context, text, Toast.LENGTH_LONG, isSucceed ? TYPE_TRUE : TYPE_FALSE);
    }

    /**
     * 显示一个有时间的吐司
     * @param context
     * @param text
     * @param time
     */
    public static void showToast(Context context, CharSequence text, int time) {
        showToast(context, text, time, TYPE_HIDE);
    }

    /**
     * 显示一个有时间有图片的吐司
     * @param context
     * @param text
     * @param time
     * @param isSucceed
     */
    public static void showToast(Context context, CharSequence text, int time, boolean isSucceed) {
        showToast(context, text, time, isSucceed ? TYPE_TRUE : TYPE_FALSE);
    }

    public void cancelToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
