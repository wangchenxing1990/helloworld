package com.technology.waangyukui.mycyclerapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by lenvo on 2018/4/24.
 */

public class MyRelativeLayout extends RelativeLayout {
    public MyRelativeLayout(Context context) {
        super(context);
        initView(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.main_my_cycler, null);
        int width = view.getWidth();
        int height = view.getHeight();
        Log.i("测量的宽和高:::", "宽" + width + "高:" + height);
        addView(view);
    }

    @Override
    protected void measureChild(View child, int parentWidthMeasureSpec, int parentHeightMeasureSpec) {
        super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("打印的是宽和高:", "宽::" + getWidth() + "高::" + getHeight() + "");
        getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawablePath(canvas);
    }

    private void drawablePath(Canvas canvas) {
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        Bitmap bitmapHeader = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_head_background);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_no_people);
        canvas.drawBitmap(bitmapHeader, 10, 10, paint);

        matrix.setTranslate(100, 100);
        canvas.drawBitmap(bitmap, matrix, paint);

        matrix.setTranslate(300, 100);
        canvas.drawBitmap(bitmap, matrix, paint);

        matrix.setTranslate(500, 100);
        canvas.drawBitmap(bitmap, matrix, paint);

        matrix.setTranslate(50, 300);
        canvas.drawBitmap(bitmap, matrix, paint);

        matrix.setTranslate(100, 500);
        canvas.drawBitmap(bitmap, matrix, paint);

        matrix.setTranslate(300, 500);
        canvas.drawBitmap(bitmap, matrix, paint);

        matrix.setTranslate(500, 500);
        canvas.drawBitmap(bitmap, matrix, paint);

        matrix.setTranslate(550, 300);
        canvas.drawBitmap(bitmap, matrix, paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }
}
