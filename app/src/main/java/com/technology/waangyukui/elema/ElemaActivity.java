package com.technology.waangyukui.elema;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.technology.waangyukui.mycyclerapp.R;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by lenvo on 2018/5/24.
 */

public class ElemaActivity extends AppCompatActivity {
    private RecyclerView recycler_view;
    private StickyListHeadersListView header_list_view;
    private RelativeLayout relative_bottom_cart;
    private ImageView cart;
    private SampleAdapter sampleAdapter;
    private LeftReAdapter leftReAdapter;
    private RelativeLayout mainActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e_le_ma);
        initView();
        initData();
        sampleAdapter = new SampleAdapter(getApplicationContext(), dataSample);
        header_list_view.setAdapter(sampleAdapter);

        leftReAdapter = new LeftReAdapter(getApplicationContext(), headerData);
        recycler_view.setAdapter(leftReAdapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler_view.setItemAnimator(new DefaultItemAnimator());

        leftReAdapter.setOnItemClickListener(new LeftReAdapter.OnItemClickListener() {
            @Override
            public void onClickListener(View view, int position) {
                int select = -1;
                for (int i = 0; i < dataSample.size(); i++) {
                    if (Integer.parseInt(dataSample.get(i).getGroupId()) == position) {
                        select = i;
                        break;
                    }
                }
                updataTypeList(position);
                header_list_view.setSelection(select);

            }
        });

        header_list_view.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                Sample sample = dataSample.get(i);
                int groupId = Integer.parseInt(sample.getGroupId());
                recycler_view.smoothScrollToPosition(groupId);
                updataTypeList(groupId);
            }
        });

        sampleAdapter.setOnOperationClickListener(new SampleAdapter.OnOperationClickListener() {
            @Override
            public void operationClickListener(View parent, View view, int position) {
                ImageView add = parent.findViewById(R.id.add);
                ImageView minus = parent.findViewById(R.id.minus);

                Sample sample = dataSample.get(position);
                int count = sample.getCount();
                switch (view.getId()) {
                    case R.id.add:
                        //添加时的动画效果
                        crateAnim(add);
                        count+=1;
                        dataSample.get(position).setCount(count);
                        boolean tag= (boolean) minus.getTag();
                        if (tag){
                        minusAnim(minus);//减号平移动画
                        }else{
                        sampleAdapter.updataCount(dataSample);
                        }
                        break;
                    case R.id.minus:
                        count-=1;
                        if (count<0){
                            count=0;
                        }
                        dataSample.get(position).setCount(count);
                        sampleAdapter.updataCount(dataSample);
                        break;
                }
            }
        });
    }

    /**
     * 减号平移动画
     */
    private void minusAnim(View minus) {
     ObjectAnimator animTranslation=ObjectAnimator.ofFloat(minus,"translationX",100,0);
     ObjectAnimator animAlpha=ObjectAnimator.ofFloat(minus,"alpha",0.0f,1.0f);
     AnimatorSet set=new AnimatorSet();
     set.playTogether(animTranslation,animAlpha);

     set.addListener(new AnimatorListenerAdapter() {
         @Override
         public void onAnimationEnd(Animator animation) {
             super.onAnimationEnd(animation);
             sampleAdapter.updataCount(dataSample);
         }
     });
        set.start();
    }

    private void crateAnim(View add) {
        int[] addCount = new int[2];
        add.getLocationInWindow(addCount);

        int[] cartCount = new int[2];
        cart.getLocationInWindow(cartCount);

        int[] mainCount = new int[2];
        mainActivity.getLocationInWindow(mainCount);

        final ImageView imageView = new ImageView(getApplicationContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dip2px(getApplicationContext(), 25), dip2px(getApplicationContext(), 25));
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.mipmap.button_add);
        imageView.setX(addCount[0]);
        imageView.setY(addCount[1] - mainCount[1]);
        mainActivity.addView(imageView);

        ValueAnimator valueAnimatorX = ValueAnimator.ofInt((int) imageView.getX(), cartCount[0]);
        valueAnimatorX.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                imageView.setTranslationX(value);
            }
        });
        valueAnimatorX.setInterpolator(new LinearInterpolator());

        ValueAnimator valueAnimatorY = ValueAnimator.ofInt((int) imageView.getY(), cartCount[1] - mainCount[1]);
        valueAnimatorY.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                imageView.setTranslationY(value);
            }
        });
        valueAnimatorY.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator addNewImage = ObjectAnimator.ofFloat(imageView, "Alpha", 10f, 0.0f);
        addNewImage.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mainActivity.removeView(imageView);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(valueAnimatorX, valueAnimatorY, addNewImage);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                cartMagnifyAnim();//购物车的放大动画
            }
        });
    }

    /**
     * 购物车的放大动画
     */
    private void cartMagnifyAnim() {
        ObjectAnimator cartAnim = ObjectAnimator.ofFloat(cart, "ScaleY", 0.6f, 1.0f);
        cartAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float value = (float) valueAnimator.getAnimatedValue();
                cart.setScaleX(value);
                cart.setScaleY(value);
            }
        });
        cartAnim.start();
    }

    private int dip2px(Context context, int dipValue) {
        Resources r = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
    }

    private void updataTypeList(int position) {
        for (int i = 0; i < headerData.size(); i++) {
            Type type = headerData.get(i);
            if (position == i) {
                type.setStatus(1);
                headerData.remove(i);
                headerData.add(i, type);
            } else {
                type.setStatus(0);
                headerData.remove(i);
                headerData.add(i, type);
            }
        }
        leftReAdapter.updataData(headerData);
    }

    /**
     * 初始化数据
     */
    private List<Type> headerData = new ArrayList();
    private List<Sample> dataSample = new ArrayList<>();
    private void initData() {
        //  int random = (int) (Math.random() * 100 + 1);
        for (int i = 0; i < 15; i++) {
            headerData.add(new Type(0, "种类" + i));
        }
        for (int i = 0; i < headerData.size(); i++) {
            for (int k = 0; k < 10; k++) {
                dataSample.add(new Sample("" + k, "" + i, "种类" + i + "   分类" + k, (int) (Math.random() * 100 + 1) + "", "种类" + i, 0));
            }
        }
    }

    /**
     * 初始化view
     */
    private void initView() {
        mainActivity = findViewById(R.id.relative_layout_parent);
        recycler_view = findViewById(R.id.recycler_view);
        header_list_view = findViewById(R.id.header_list_view);
        relative_bottom_cart = findViewById(R.id.relative_bottom_cart);
        cart = findViewById(R.id.image_view_cart);
    }
}
