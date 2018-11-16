package com.technology.waangyukui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lenvo on 2018/6/22.
 */

public class BezierView extends View {


    private Paint bezierPaint;
    private Paint paintAuxiliary;
    private Paint paintAuxiliaryText;
    private int mPointStartX;
    private int mPointStartY;
    private int mPointEndX;
    private int mPointEndY;
    private float mAuxiliaryX;
    private float mAuxiliaryY;
    private Path mPath=new Path();
    public BezierView(Context context) {
        super(context);

    }

    public BezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        bezierPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        bezierPaint.setColor(0xffff00ff);
        bezierPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        bezierPaint.setStrokeWidth(5);

        paintAuxiliary=new Paint(Paint.ANTI_ALIAS_FLAG);
        paintAuxiliary.setStyle(Paint.Style.STROKE);
        paintAuxiliary.setStrokeWidth(2);

        paintAuxiliaryText=new Paint(Paint.ANTI_ALIAS_FLAG);
        paintAuxiliaryText.setStyle(Paint.Style.STROKE);
        paintAuxiliaryText.setTextSize(20);

    }

    public BezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPointStartX=w/4;
        mPointStartY=h/2-200;

        mPointEndX=w*3/4;
        mPointEndY=h/2-200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mPointStartX,mPointStartY);

        //辅助点
        canvas.drawPoint(mAuxiliaryX,mAuxiliaryY,paintAuxiliary);
        canvas.drawText("控制点",mAuxiliaryX,mAuxiliaryY,paintAuxiliaryText);
        canvas.drawText("起始点",mPointStartX,mPointStartY,paintAuxiliaryText);
        canvas.drawText("终止点",mPointEndX,mPointEndY,paintAuxiliaryText);

        canvas.drawLine(mPointStartX,mPointStartY,mAuxiliaryX,mAuxiliaryY,paintAuxiliary);
        canvas.drawLine(mPointEndX,mPointEndY,mAuxiliaryX,mAuxiliaryY,paintAuxiliary);
        canvas.drawLine(mPointStartX,mPointStartY,mPointEndX,mPointEndY,paintAuxiliary);

        mPath.quadTo(mAuxiliaryX,mAuxiliaryY,mPointEndX,mPointEndY);
        canvas.drawPath(mPath,bezierPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                mAuxiliaryX=event.getX();
                mAuxiliaryY=event.getY();
                invalidate();
        }
        return true;
    }
}
