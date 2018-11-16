package com.technology.waangyukui.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lenvo on 2018/6/22.
 */

public class PathView extends View {
    public PathView(Context context) {
        super(context);
        initView();
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private Path path;
    private Paint paint;

    private void initView() {
        paint = new Paint();
        paint.setColor(0xffff00ff);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(false);
        path = new Path();
//        path.lineTo(400, 500);
//        path.moveTo(300, 300);
//        path.lineTo(900, 800);
//        path.close();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);

        path.lineTo(400,500);
      //  path.setLastPoint(300,300);
        path.lineTo(900,800);
        path.lineTo(200,700);
        path.close();
        canvas.drawPath(path,paint);

        canvas.translate(300,500);
        path.addRect(0,0,400,400,Path.Direction.CW);
        canvas.drawPath(path,paint);
    }
}
