package com.technology.waangyukui.layout;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/5/30.
 */

public class SkidRightActivityTwo extends AppCompatActivity {
    private ImageView mImgBg;
    private ImageView mImgGif;
    private TextView mTvTitle;
    private  int mImgPath;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skid_two);
        mImgBg = findViewById(R.id.img_bg);
        mTvTitle = findViewById(R.id.tv_title);
        mImgGif = findViewById(R.id.img_gif);
        if (getIntent() != null){
            mImgPath = getIntent().getIntExtra("img",R.mipmap.skid_right_3);
            String title = getIntent().getStringExtra("title");
            mTvTitle.setText(title);
            Glide.with(this).load(mImgPath).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImgBg);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Glide.with(SkidRightActivityTwo.this).load(mImgPath).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImgGif);
                }
            },100);

        }

        mImgGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mImgGif.setVisibility(View.INVISIBLE);
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        mImgGif.setVisibility(View.INVISIBLE);
        super.onBackPressed();
    }

}
