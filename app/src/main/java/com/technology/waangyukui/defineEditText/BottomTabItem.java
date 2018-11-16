package com.technology.waangyukui.defineEditText;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.waangyukui.mycyclerapp.R;


/**
 * Created by lenvo on 2018/6/5.
 */

public class BottomTabItem extends RelativeLayout {
    private ImageView imageViewTab;
    private TextView textViewTab;
    public BottomTabItem(Context context) {
        super(context);
        initView(context);
    }

    public BottomTabItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public BottomTabItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View rootView= LayoutInflater.from(context).inflate(R.layout.item_bottom_tab,null);
        imageViewTab=rootView.findViewById(R.id.image_view_tab);
        textViewTab=rootView.findViewById(R.id.text_view_tab);
        setGravity(Gravity.CENTER);
        addView(rootView);
    }

    public void setTextAndImage(int imageId,int textId){
        imageViewTab.setImageResource(imageId);
        textViewTab.setText(textId);
    }

    @Override
    public void setSelected(boolean selected) {
        imageViewTab.setSelected(selected);
        textViewTab.setSelected(selected);
        super.setSelected(selected);
    }

    @Override
    public void setEnabled(boolean enabled) {
        imageViewTab.setEnabled(enabled);
        textViewTab.setEnabled(enabled);
        super.setEnabled(enabled);
    }
}
