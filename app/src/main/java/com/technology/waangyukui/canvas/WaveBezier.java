package com.technology.waangyukui.canvas;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by lenvo on 2018/6/22.
 */

public class WaveBezier extends View implements View.OnClickListener {

    private Path mPath;
    private Paint mPaint;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mWaveCount;
    private int mWaveLength;
    private int mCenterY;
    private int mOffset;

    public WaveBezier(Context context) {
        super(context);
    }

    public WaveBezier(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0xff00ffff);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        setOnClickListener(this);
    }

    public WaveBezier(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWaveLength = h;
        mScreenHeight = h;
        mScreenWidth = w;
        mWaveCount = (int) Math.round(mScreenWidth / mWaveLength + 1.5);
        mCenterY = mScreenHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(-mWaveLength + mOffset, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            mPath.quadTo((-mWaveLength * 3 / 4) + (i * mWaveLength) + mOffset, mCenterY + 100, (-mWaveLength / 2) + (i * mWaveLength) + mOffset, mCenterY);
            mPath.quadTo((-mWaveLength / 4) + (i * mWaveLength) + mOffset, mCenterY - 100, i * mWaveLength + mOffset, mCenterY);
        }
        mPath.lineTo(mScreenWidth, mScreenHeight);
        mPath.lineTo(0, mScreenHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public void onClick(View view) {
        ValueAnimator animator = ValueAnimator.ofInt(0, mWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
