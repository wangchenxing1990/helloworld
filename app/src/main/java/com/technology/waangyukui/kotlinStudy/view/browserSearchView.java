package com.technology.waangyukui.kotlinStudy.view;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by wangxueping on 2018/1/10.
 */

public class browserSearchView extends RelativeLayout {

    private TextView iconView;
    private TextView palaceHolder;
    private View sperateView;
    public  browserSearchView(Context context){
        super(context);
        intview(context);
    }
    public browserSearchView(Context context, AttributeSet attrs) {
        super(context,attrs);
        intview(context);
    }

    public void intview(Context context) {
        LayoutInflater.from(context).inflate(R.layout.browser_search, this, true);
        iconView  = (TextView)findViewById(R.id.browsersearchicon) ;
        palaceHolder = (TextView)findViewById(R.id.browsersearchhint) ;
        sperateView = (View)findViewById(R.id.browsersearchline);
        Typeface iconfont = Typeface.createFromAsset(context.getAssets(), "iconfont/iconfont.ttf");
        iconView.setTypeface(iconfont);
        iconView.setText("\ue741");
    }
}
