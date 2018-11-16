package com.technology.waangyukui.mycyclerapp;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by lenvo on 2018/5/23.
 */

public class DragView extends RelativeLayout {
    private int mLastX;
    private int mLastY;
    private Scroller mScroller;

    public DragView(Context context) {
        super(context);
        init(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        //setBackgroundColor(Color.BLUE);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
//                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //int offsetX = x - mLastX;
                //int offsetY = y - mLastY;
                //此时，计算坐标是相反的
//                int offsetX = mLastX - x;
                int offsetY = mLastY - y;
                Log.i("滑动中的offsety的变化情况:",offsetY+"");
                //让View所在的ViewGroup进行移动
                ((View)getParent()).scrollBy(0,offsetY);
                break;
            case MotionEvent.ACTION_UP:
                View viewGroup = (View) getParent();
                mScroller.startScroll(viewGroup.getScrollX(),viewGroup.getScrollY(),-viewGroup.getScrollX(),-viewGroup.getScrollY());
                //记住需要invalidate
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScroller.getCurrX(),mScroller.getCurrY());
            //记住，需要不断调用invalidate进行重绘
            invalidate();
        }
    }
}
