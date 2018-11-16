package com.technology.waangyukui.defineEditText;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.waangyukui.mycyclerapp.R;

import org.w3c.dom.Text;

/**
 * Created by lenvo on 2018/6/4.
 */

public class DefineItemRelative extends RelativeLayout {
    private Context context;
    private String leftTitle;
    private String rightTitle;
    private String centerTitle;
    private int leftSrcId;
    private int rightSrcId;
    private float leftPadding;
    private float rightPadding;
    private float leftMargin;
    private float rightMargin;
    private float leftTVSize;
    private float rightTVSize;
    private View contentView;
    private ImageView image_left;
    private ImageView image_right;
    private TextView text_left;
    private TextView text_right;

    public DefineItemRelative(Context context) {
        super(context);
        this.context = context;
    }

    public DefineItemRelative(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
    }

    public DefineItemRelative(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.item_relative_layout);

        leftTitle = typedArray.getString(R.styleable.item_relative_layout_title_left);
        rightTitle = typedArray.getString(R.styleable.item_relative_layout_title_right);
        centerTitle = typedArray.getString(R.styleable.item_relative_layout_title_center);

        leftSrcId = typedArray.getResourceId(R.styleable.item_relative_layout_image_left_src, -1);
        rightSrcId = typedArray.getResourceId(R.styleable.item_relative_layout_image_right_src, -1);

        leftPadding = typedArray.getDimension(R.styleable.item_relative_layout_left_image_padding, 0);
        rightPadding = typedArray.getDimension(R.styleable.item_relative_layout_right_image_padding, 0);
        leftMargin = typedArray.getDimension(R.styleable.item_relative_layout_left_margin, 0);
        rightMargin = typedArray.getDimension(R.styleable.item_relative_layout_right_margin, 0);

        leftTVSize = typedArray.getDimension(R.styleable.item_relative_layout_leftTvSize, 0);
        rightTVSize = typedArray.getDimension(R.styleable.item_relative_layout_rightTvSize, 0);

        typedArray.recycle();
        initView();
    }

    private void initView() {
        contentView = LayoutInflater.from(context).inflate(R.layout.define_item_relative_layout, null);
        addView(contentView);
        image_left = contentView.findViewById(R.id.image_left);
        image_right = contentView.findViewById(R.id.image_right);
        text_left = contentView.findViewById(R.id.text_left);
        text_right = contentView.findViewById(R.id.text_right);

        image_left.setImageResource(leftSrcId);
        image_right.setImageResource(rightSrcId);

        text_left.setText(leftTitle);
        text_right.setText(rightTitle);

        text_left.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTVSize + 1);
        text_right.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTVSize + 1);

        image_left.setPadding((int)leftPadding,(int)leftPadding,(int)leftMargin,(int)leftPadding);
        image_right.setPadding((int)rightMargin,(int)rightPadding,(int)rightPadding,(int)rightPadding);



    }
}
