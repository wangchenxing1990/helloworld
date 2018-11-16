package com.technology.waangyukui.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dingmouren.layoutmanagergroup.skidright.SkidRightLayoutManager;
import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/5/30.
 */

public class SkidRightActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skid_activity_right_1);
        initView();
    }

    private RecyclerView recycler_view;
    private SkidRightLayoutManager skidRightLayoutManager;


    private void initView() {
        recycler_view = findViewById(R.id.recycler_view);
        skidRightLayoutManager = new SkidRightLayoutManager(1.5f, 0.85f);
        recycler_view.setLayoutManager(skidRightLayoutManager);
        recycler_view.setAdapter(new MyAdapter(SkidRightActivity.this));
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        private final Activity skidRightActivity;

        public MyAdapter(Activity skidRightActivity){
           this.skidRightActivity=skidRightActivity;
        }
        private int[] imgs = {
                R.mipmap.skid_right_1,
                R.mipmap.skid_right_2,
                R.mipmap.skid_right_3,
                R.mipmap.skid_right_4,
                R.mipmap.skid_right_5,
                R.mipmap.skid_right_6,
                R.mipmap.skid_right_7
        };
        String[] titles = {"Acknowl", "Belief", "Confidence", "Dreaming", "Happiness", "Confidence"};

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LinearLayout.inflate(parent.getContext(), R.layout.item_skid, null);
            MyViewHolder vh = new MyViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(final MyAdapter.MyViewHolder holder, final int position) {
            Glide.with(getApplicationContext()).load(imgs[position % 7]).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.img_bg);
            holder.tv_title.setText(titles[position % 6]);

            holder.img_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SkidRightActivity.this, SkidRightActivityTwo.class);
                    intent.putExtra("img", imgs[position % 7]);
                    intent.putExtra("title", titles[position % 6]);
                    Pair<View, String> p1 = Pair.create((View)holder.img_bg, "img_view_1");
                    Pair<View, String> p2 = Pair.create((View)holder.tv_title, "title_1");
                    Pair<View, String> p3 = Pair.create((View)holder.tv_bottom, "tv_bottom");
                    ActivityOptionsCompat options = ActivityOptionsCompat
                            .makeSceneTransitionAnimation(skidRightActivity,p1, p2, p3);
                    startActivity(intent,options.toBundle());
                }
            });

        }

        @Override
        public int getItemCount() {
            return 20;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            private ImageView img_bg;
            private TextView tv_title;
            private TextView tv_bottom;

            public MyViewHolder(View itemView) {
                super(itemView);
                img_bg = itemView.findViewById(R.id.img_bg);
                tv_title = itemView.findViewById(R.id.tv_title);
                tv_bottom = itemView.findViewById(R.id.tv_bottom);
            }
        }
    }
}
