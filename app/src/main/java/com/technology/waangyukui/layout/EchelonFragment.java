package com.technology.waangyukui.layout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dingmouren.layoutmanagergroup.echelon.EchelonLayoutManager;
import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/5/30.
 */

public class EchelonFragment extends Fragment {
    private RecyclerView recyclerView;
    private EchelonLayoutManager echelonLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_echelon,container,false);
        recyclerView=view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    private void initData() {
        echelonLayout=new EchelonLayoutManager(getContext());
        recyclerView.setLayoutManager(echelonLayout);
        recyclerView.setAdapter(new MyEchelonAdapter());
    }

    class MyEchelonAdapter extends RecyclerView.Adapter<MyEchelonAdapter.EchelonHolder> {
        private int[] icons = {R.mipmap.header_icon_1,R.mipmap.header_icon_2,R.mipmap.header_icon_3,R.mipmap.header_icon_4};
        private int[] bgs = {R.mipmap.bg_1,R.mipmap.bg_2,R.mipmap.bg_3,R.mipmap.bg_4};
        private String[] nickNames = {"左耳近心","凉雨初夏","稚久九栀","半窗疏影"};
        private String[] descs = {
                "回不去的地方叫故乡 没有根的迁徙叫流浪...",
                "人生就像迷宫，我们用上半生找寻入口，用下半生找寻出口",
                "原来地久天长，只是误会一场",
                "不是故事的结局不够好，而是我们对故事的要求过多",
                "只想优雅转身，不料华丽撞墙"
        };
        @Override
        public MyEchelonAdapter.EchelonHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LinearLayout.inflate(getContext(),R.layout.item_echelon,null);
            EchelonHolder vh=new EchelonHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyEchelonAdapter.EchelonHolder holder, int position) {
            holder.icon.setImageResource(icons[position%4]);
            holder.nickName.setText(nickNames[position%4]);
            holder.desc.setText(descs[position%5]);
            holder.bg.setImageResource(bgs[position%4]);
        }

        @Override
        public int getItemCount() {
            return 60;
        }

        public class EchelonHolder extends RecyclerView.ViewHolder{
            ImageView icon;
            ImageView bg;
            TextView nickName;
            TextView desc;
            public EchelonHolder(View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.img_icon);
                bg = itemView.findViewById(R.id.img_bg);
                nickName = itemView.findViewById(R.id.tv_nickname);
                desc = itemView.findViewById(R.id.tv_desc);
            }
        }
    }
}
