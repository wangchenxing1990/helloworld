package com.technology.waangyukui.tools;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by lenvo on 2018/6/5.
 */

public class ScreenUtils {
    public static int dp2px(Context context,int dp){
        DisplayMetrics displayMetrics=context.getResources().getDisplayMetrics();
        return (int) (dp*displayMetrics.density+0.5);
    }

    public static int getScreenWidth(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
