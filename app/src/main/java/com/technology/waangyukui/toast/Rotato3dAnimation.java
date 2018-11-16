package com.technology.waangyukui.toast;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

/**
 * Created by lenvo on 2018/6/6.
 */

public class Rotato3dAnimation extends Animation {

    private Camera camera=new Camera();
    private float mCenterX;
    private float mCenterY;

//    public Rotato3dAnimation(float centerX,float centerY){
//        mCenterX=centerX;
//        mCenterY=centerY;
//    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        //获得中心点坐标
        mCenterX = width / 2;
        mCenterY = width / 2;
        //动画执行时间 自行定义
        setDuration(3 * 1000);
        setInterpolator(new DecelerateInterpolator());
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
//        final Camera camera=mCamera;
//        Matrix matrix=t.getMatrix();
//        camera.save();
//        camera.rotateY(180);
//        camera.getMatrix(matrix);
//
//        camera.restore();
//        matrix.preTranslate(-mCenterX, -mCenterY);
//        matrix.postTranslate(mCenterX, mCenterY);

        final Matrix matrix = t.getMatrix();
        camera.save();
        //中心是Y轴旋转，这里可以自行设置X轴 Y轴 Z轴
        camera.rotateY(360 * interpolatedTime);
        //把我们的摄像头加在变换矩阵上
        camera.getMatrix(matrix);
        //设置翻转中心点
        matrix.preTranslate(-mCenterX, -mCenterY);
        matrix.postTranslate(mCenterX,mCenterY);
        camera.restore();

    }
}
