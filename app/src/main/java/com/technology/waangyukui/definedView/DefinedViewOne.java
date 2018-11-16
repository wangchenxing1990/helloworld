package com.technology.waangyukui.definedView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/8/6.
 */

public class DefinedViewOne extends View {
    private Context context;
    private Paint bPaint;
    private Bitmap bitmapBack;
    private Bitmap bitmapPeopleBack, bitHavePeople, bitNoPeople;
    private Bitmap bitmapBig, bitmapOma,bitmapDeZhou;
    private RectF rectf_p;
    private RectF rectf_big;
    private RectF rectPeopleBack;
    private int count = 9;
    private int playMode, player;
    private int viewWidth = 750, viewHeight = 250;

    public DefinedViewOne(Context context) {
        super(context);
        this.context = context;
    }

    public DefinedViewOne(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initBitmap();
        initView(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        //计算宽
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = 750;
        }
        //计算高
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = 240;
        }
        viewWidth = width;
        viewHeight = height;
        setMeasuredDimension(width, height);
    }

    private void initBitmap() {
        bitmapBack = BitmapFactory.decodeResource(getResources(), R.drawable.icon_background_poker);
        bitmapBig = BitmapFactory.decodeResource(getResources(), R.drawable.icon_big_pin);
        bitmapOma = BitmapFactory.decodeResource(getResources(), R.drawable.icon_omaha);
        bitmapDeZhou = BitmapFactory.decodeResource(getResources(), R.drawable.icon_de_poker);
        bitmapPeopleBack = BitmapFactory.decodeResource(getResources(), R.drawable.icon_people_background);
        bitHavePeople = BitmapFactory.decodeResource(getResources(), R.drawable.icon_have_people);
        bitNoPeople = BitmapFactory.decodeResource(getResources(), R.drawable.icon_no_people);
    }

    private void initView(Context context) {
        bPaint = new Paint();
        bPaint.setAntiAlias(true);
        bPaint.setStyle(Paint.Style.FILL);
        bPaint.setStrokeCap(Paint.Cap.ROUND);

        rectf_p = new RectF(0, 15, viewWidth, viewHeight);
        rectf_big = new RectF((viewWidth - bitmapBig.getWidth()) / 2, (viewHeight - 15) / 2 - bitmapBig.getHeight() + 15, (viewWidth + bitmapBig.getWidth()) / 2, (viewHeight - 15) / 2 + bitmapBig.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapBack, null, rectf_p, bPaint);//背景图片
        if (playMode == 0) {
            canvas.drawBitmap(bitmapDeZhou, null, rectf_big, bPaint);//中间的字体
        }

        if (playMode == 1) {
            canvas.drawBitmap(bitmapOma, null, rectf_big, bPaint);//中间的字体
        }

        if (playMode == 2) {
            canvas.drawBitmap(bitmapBig, null, rectf_big, bPaint);//中间的字体
        }


        if (count == 9) {
            for (int i = 0; i < 9; i++) {
                if (i == 0) {
                    rectPeopleBack = new RectF((viewWidth / 4 - bitmapPeopleBack.getWidth()) / 2, (viewHeight - 15) / 2 + 15, (viewWidth / 4 + bitmapPeopleBack.getWidth()) / 2, (viewHeight - 15) / 2 + bitmapPeopleBack.getHeight() + 15);
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }

                if (i == 1) {
                    rectPeopleBack = new RectF((viewWidth / 4 - bitmapPeopleBack.getWidth()) / 2, 20, (viewWidth / 4 + bitmapPeopleBack.getWidth()) / 2, 20 + bitmapPeopleBack.getHeight());
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }

                if (i == 2) {
                    rectPeopleBack = new RectF((viewWidth / 3 - bitmapPeopleBack.getWidth()/ 2) , 15, (viewWidth / 3 + bitmapPeopleBack.getWidth()/ 2) , 15 + bitmapPeopleBack.getHeight());
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }

                if (i == 3) {
                    rectPeopleBack = new RectF((viewWidth / 2 - bitmapPeopleBack.getWidth() / 2), 15, (viewWidth / 2 + bitmapPeopleBack.getWidth() / 2), 15 + bitmapPeopleBack.getHeight());
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }

                if (i == 4) {
                    rectPeopleBack = new RectF((viewWidth * 2 / 3 - bitmapPeopleBack.getWidth() / 2), 15, (viewWidth * 2 / 3 + bitmapPeopleBack.getWidth() / 2), 15 + bitmapPeopleBack.getHeight());
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }

                if (i == 5) {
                    rectPeopleBack = new RectF((viewWidth - bitmapPeopleBack.getWidth()), viewHeight / 2 - bitmapPeopleBack.getHeight() / 2, (viewWidth), (viewHeight + bitmapPeopleBack.getHeight()) / 2);
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }

                if (i == 6) {
                    rectPeopleBack = new RectF((viewWidth * 2 / 3- bitmapPeopleBack.getWidth() / 2), viewHeight - bitmapPeopleBack.getHeight(), (viewWidth * 2 / 3 + bitmapPeopleBack.getWidth() / 2), viewHeight);
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }

                if (i == 7) {
                    rectPeopleBack = new RectF((viewWidth/2 - bitmapPeopleBack.getWidth() / 2), viewHeight - bitmapPeopleBack.getHeight(), (viewWidth / 2 + bitmapPeopleBack.getWidth() / 2), viewHeight);
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }

                if (i == 8) {
                    rectPeopleBack = new RectF((viewWidth/3 - bitmapPeopleBack.getWidth()/ 2) , viewHeight - bitmapPeopleBack.getHeight(), (viewWidth / 3 + bitmapPeopleBack.getWidth()/ 2) , viewHeight);
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }
            }
        }

        if (count == 6) {
            for (int i = 0; i < 6; i++) {
                if (i == 0) {
                    rectPeopleBack = new RectF((viewWidth / 2 - bitmapPeopleBack.getWidth()) / 2, 15, (viewWidth / 2 + bitmapPeopleBack.getWidth()) / 2, 15 + bitmapPeopleBack.getHeight());
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }
                if (i == 1) {
                    rectPeopleBack = new RectF((viewWidth / 2 - bitmapPeopleBack.getWidth() / 2), 15, (viewWidth / 2 + bitmapPeopleBack.getWidth() / 2), 15 + bitmapPeopleBack.getHeight());
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }
                if (i == 2) {
                    rectPeopleBack = new RectF((viewWidth * 3 / 4 - bitmapPeopleBack.getWidth() / 2), 15, (viewWidth * 3 / 4 + bitmapPeopleBack.getWidth() / 2), 15 + bitmapPeopleBack.getHeight());
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }

                if (i == 3) {
                    rectPeopleBack = new RectF((viewWidth * 3 / 4 - bitmapPeopleBack.getWidth() / 2), viewHeight - bitmapPeopleBack.getHeight(), (viewWidth * 3 / 4 + bitmapPeopleBack.getWidth() / 2), viewHeight);
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }
                if (i == 4) {
                    rectPeopleBack = new RectF((viewWidth / 2 - bitmapPeopleBack.getWidth() / 2), viewHeight - bitmapPeopleBack.getHeight(), (viewWidth / 2 + bitmapPeopleBack.getWidth() / 2), viewHeight);
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }
                if (i == 5) {
                    rectPeopleBack = new RectF((viewWidth / 2 - bitmapPeopleBack.getWidth()) / 2, viewHeight - bitmapPeopleBack.getHeight(), (viewWidth / 2 + bitmapPeopleBack.getWidth()) / 2, viewHeight);
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }
            }
        }

        if (count == 3) {
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    rectPeopleBack = new RectF((viewWidth / 2 - bitmapPeopleBack.getWidth()) / 2, 15, (viewWidth / 2 + bitmapPeopleBack.getWidth()) / 2, 15 + bitmapPeopleBack.getHeight());
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }
                if (i == 1) {
                    rectPeopleBack = new RectF((viewWidth * 3 / 4 - bitmapPeopleBack.getWidth() / 2), 15, (viewWidth * 3 / 4 + bitmapPeopleBack.getWidth() / 2), 15 + bitmapPeopleBack.getHeight());
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }

                if (i == 2) {
                    rectPeopleBack = new RectF((viewWidth / 2 - bitmapPeopleBack.getWidth() / 2), viewHeight - bitmapPeopleBack.getHeight(), (viewWidth / 2 + bitmapPeopleBack.getWidth() / 2), viewHeight + 15);
                    canvas.drawBitmap(bitmapPeopleBack, null, rectPeopleBack, bPaint);
                    canvas.drawBitmap(player > i ? bitHavePeople : bitNoPeople, null, rectPeopleBack, bPaint);
                }

            }
        }
    }


    public void setDataDisplay(int count, int playMode, int player) {
        this.count = count;
        this.playMode = playMode;
        this.player = player;
        invalidate();
    }
}
