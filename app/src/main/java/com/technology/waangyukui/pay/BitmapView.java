package com.technology.waangyukui.pay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/8/2.
 */

public class BitmapView extends View {
    private Context context;

    public BitmapView(Context context) {
        super(context);
//        this.context = context;
        initBitmapView(context);
    }

    public BitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //        this.context = context;
        initBitmapView(context);
    }


    public BitmapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //        this.context = context;
        initBitmapView(context);
    }

    private Bitmap bitmap;
    int height, width;

    private void initBitmapView(Context context) {
        bPaint = new Paint();
        bPaint.setAntiAlias(true);
        //  bPaint.setColor(backgroundColor);
        bPaint.setStyle(Paint.Style.FILL);
        bPaint.setStrokeCap(Paint.Cap.ROUND);

        tPaint = new Paint();
        tPaint.setAntiAlias(true);
        tPaint.setColor(getResources().getColor(R.color.colortheme));
        tPaint.setStyle(Paint.Style.FILL);
        tPaint.setStrokeCap(Paint.Cap.ROUND);

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.icon_head_background);
        height = bitmap.getHeight();
        width = bitmap.getWidth();

        DisplayMetrics metrics = new DisplayMetrics();
        metrics = context.getApplicationContext().getResources().getDisplayMetrics();
        viewWidth = metrics.widthPixels;
        viewHeight = metrics.heightPixels;
    }

    private int viewWidth, viewHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;
        //计算宽
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
            Log.i("viewWidth1233654width", width + "");
        } else {
            // width = getResources().getDimensionPixelSize(R.dimen.x750);
        }
        //计算高
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
            Log.i("viewWidth1233654 height", height + "");
        } else {
            // height = getResources().getDimensionPixelSize(R.dimen.x80);
        }
        viewWidth = width;
        viewHeight = height;
        setMeasuredDimension(width, height);
        Log.i("viewWidth1233654", viewWidth + "");
    }


    private RectF rectf_b;//背景圆角矩形
    private Paint bPaint, tPaint;//背景进度画笔
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectf_b = new RectF(0, 0, viewWidth, viewHeight);
        Rect rect = new Rect();
        tPaint.getTextBounds("王玉奎", 0, "王玉奎".length(), rect);

        canvas.drawBitmap(bitmap, null, rectf_b, bPaint);
        int x = rect.width();
        int y = rect.height();
        Log.i("王玉奎的长度：：：", x + "");
        Log.i("王玉奎的高度：：：", y + "");
        canvas.drawText("王玉奎", viewWidth / 2 - x / 2, viewHeight / 2 - y / 2, tPaint);
    }
}
