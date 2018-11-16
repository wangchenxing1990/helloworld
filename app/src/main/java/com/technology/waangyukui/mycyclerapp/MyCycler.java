package com.technology.waangyukui.mycyclerapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by lenvo on 2018/4/23.
 */

public class MyCycler extends View {

    int width, height,iconWidth,iconHeight;

    public MyCycler(Context context) {
        super(context);
        init(context);
    }

    public MyCycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyCycler(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackgroundResource(R.drawable.icon_head_background);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        width = getMeasuredWidth();
//        height = getMeasuredHeight();
//        Log.i("打印的是宽和高:aaaa", "宽::" + width + "高::" + height + "");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawablePath(canvas);
    }

    private void drawablePath(Canvas canvas) {
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.button_add);

        height = getMeasuredHeight();
        width = getMeasuredWidth();
        iconWidth=bitmap.getHeight();
        iconHeight=bitmap.getHeight();

        Log.i("打印的是宽和高:aaaa", "宽::" + width + "高::" + height + "");
        matrix.setTranslate(0, (height / 2)-(iconHeight/2));
        canvas.drawBitmap(bitmap, matrix, paint);

        matrix.setTranslate((width / 2)-(iconWidth/2), 0);
        canvas.drawBitmap(bitmap, matrix, paint);

        matrix.setTranslate((width / 2)-(iconWidth/2), height-iconWidth);
        canvas.drawBitmap(bitmap, matrix, paint);

        matrix.setTranslate(width-iconWidth, (height / 2)-(iconHeight/2));
        canvas.drawBitmap(bitmap, matrix, paint);

        Bitmap bitmapBackground=BitmapFactory.decodeResource(getResources(),R.mipmap.icon_head_background);

    }
}

//        matrix.setTranslate(300, 100);
//        canvas.drawBitmap(bitmap, matrix, paint);
//
//        matrix.setTranslate(500, 100);
//        canvas.drawBitmap(bitmap, matrix, paint);
//
//        matrix.setTranslate(50, 300);
//        canvas.drawBitmap(bitmap, matrix, paint);
//
//        matrix.setTranslate(100, 500);
//        canvas.drawBitmap(bitmap, matrix, paint);
//
//        matrix.setTranslate(300, 500);
//        canvas.drawBitmap(bitmap, matrix, paint);
//
//        matrix.setTranslate(500, 500);
//        canvas.drawBitmap(bitmap, matrix, paint);
//
//        matrix.setTranslate(550, 300);
//        canvas.drawBitmap(bitmap, matrix, paint);
//
//        canvas.scale(0.8f,0.8f);
//        canvas.drawBitmap(bitmap, matrix, paint);


