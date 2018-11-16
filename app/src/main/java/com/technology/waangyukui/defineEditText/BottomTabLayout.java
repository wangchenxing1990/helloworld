package com.technology.waangyukui.defineEditText;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.technology.waangyukui.mycyclerapp.R;
import com.technology.waangyukui.tools.ScreenUtils;

/**
 * Created by lenvo on 2018/6/5.
 */

public class BottomTabLayout extends LinearLayout {
    private int count;

    public BottomTabLayout(Context context) {
        this(context, null);
        initLayout(context);
    }

    public BottomTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public BottomTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    private void initLayout(Context context) {
        TypedArray typedArray = context.obtainStyledAttributes(R.styleable.BottomTabLayout);
        count = typedArray.getInteger(R.styleable.BottomTabLayout_count, 4);
        int heightItem = ScreenUtils.dp2px(context, 48);
        int widthItem = ScreenUtils.getScreenWidth(context) / 4;
        setOrientation(HORIZONTAL);
//        setGravity(Gravity.CENTER_VERTICAL);
        setBackgroundColor(context.getResources().getColor(R.color.home_tab_bg_color));
        LayoutParams layoutParams = new LayoutParams(widthItem, LayoutParams.MATCH_PARENT);
        for (int i = 0; i < count; i++) {
            BottomTabItem bottomTabItem = new BottomTabItem(context);
            bottomTabItem.setEnabled(true);
            addView(bottomTabItem, layoutParams);
        }
    }
}
