package com.technology.waangyukui.elema;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.waangyukui.mycyclerapp.R;

import java.util.List;

/**
 * Created by lenvo on 2018/5/25.
 */

public class LeftReAdapter extends RecyclerView.Adapter<LeftReAdapter.MyViewHolder> {

    private List<Type> data;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public LeftReAdapter(Context context, List<Type> data) {
        this.context=context;
        this.data = data;
    }

    public interface OnItemClickListener {
        void onClickListener(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public LeftReAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_recycler_item, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(LeftReAdapter.MyViewHolder holder, int position) {
        holder.left_tv.setText(data.get(position).getType());
        holder.left_tv.setTag(position);

        if (data.get(position).getStatus() == 0) {
            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.gray));
            holder.left_tv.setTextColor(context.getResources().getColor(R.color.gray_text));
        }
        if (data.get(position).getStatus() == 1) {
            holder.layout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
            holder.left_tv.setTextColor(context.getResources().getColor(android.R.color.black));
        }

        if (onItemClickListener != null) {
            holder.left_tv.setOnClickListener(new BasicOnClickListener(holder));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updataData(List<Type> data){
        this.data=data;
        notifyDataSetChanged();
    }

    public class BasicOnClickListener implements View.OnClickListener {

        private final MyViewHolder holder;

        public BasicOnClickListener(MyViewHolder holder) {
            this.holder = holder;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.left_tv:
                    onItemClickListener.onClickListener(holder.left_tv, holder.getLayoutPosition());
                    break;
            }
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView left_tv;
        private RelativeLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            left_tv = itemView.findViewById(R.id.left_tv);
            layout = itemView.findViewById(R.id.layout);
        }
    }
}
