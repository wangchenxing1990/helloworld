package com.technology.waangyukui.elema;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technology.waangyukui.mycyclerapp.R;

import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by lenvo on 2018/5/24.
 */

public class SampleAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private Context applicationContext;
    private List<Sample> dataSample;
    private LayoutInflater layoutInflater;
    private OnOperationClickListener operationClickListener;

    public SampleAdapter(Context applicationContext, List<Sample> dataSample) {
        this.applicationContext = applicationContext;
        this.dataSample = dataSample;
        layoutInflater = LayoutInflater.from(applicationContext);
    }

    public void updataCount(List<Sample> dataSample) {
        this.dataSample=dataSample;
        notifyDataSetChanged();
    }

    interface OnOperationClickListener{
        void operationClickListener(View parent, View view, int position);
    }

    public void setOnOperationClickListener(OnOperationClickListener onOperationClickListener){
        this.operationClickListener=onOperationClickListener;
    }
    @Override
    public int getCount() {
        return dataSample.size();
    }

    @Override
    public Object getItem(int i) {
        return dataSample.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemViewHolder itemViewHolder;
        if (view == null) {
            itemViewHolder = new ItemViewHolder();
            view = layoutInflater.inflate(R.layout.item_cart_shopping, null);
            itemViewHolder.imageView = view.findViewById(R.id.imageView);
            itemViewHolder.add = view.findViewById(R.id.add);
            itemViewHolder.minus = view.findViewById(R.id.minus);
            itemViewHolder.count = view.findViewById(R.id.count);
            itemViewHolder.desc = view.findViewById(R.id.desc);
            itemViewHolder.title = view.findViewById(R.id.title);
            itemViewHolder.add = view.findViewById(R.id.add);
            itemViewHolder.parent = view.findViewById(R.id.item_parent);

            view.setTag(itemViewHolder);
        } else {
            itemViewHolder = (ItemViewHolder) view.getTag();
        }

        itemViewHolder.title.setText(dataSample.get(i).getTitle());
        itemViewHolder.desc.setText(dataSample.get(i).getDesc());
        itemViewHolder.add.setVisibility(View.VISIBLE);
//        itemViewHolder.add.setAlpha(1f);
        if (dataSample.get(i).getCount() == 0) {
            itemViewHolder.count.setAlpha(0f);
            itemViewHolder.minus.setAlpha(0f);
            itemViewHolder.minus.setTag(true);
        } else {
            itemViewHolder.count.setAlpha(1f);
            itemViewHolder.count.setText(dataSample.get(i).getCount() + "");
            itemViewHolder.minus.setAlpha(1f);
            itemViewHolder.minus.setTag(false);
        }

        if (operationClickListener!=null){
            itemViewHolder.add.setOnClickListener(new BaseOnClickListener(itemViewHolder,i));
            itemViewHolder.minus.setOnClickListener(new BaseOnClickListener(itemViewHolder,i));
        }

        return view;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder headerViewHolder;
        if (convertView == null) {
            headerViewHolder = new HeaderViewHolder();
            convertView = layoutInflater.inflate(R.layout.header_item, null);
            headerViewHolder.textView = convertView.findViewById(R.id.header);

            convertView.setTag(headerViewHolder);
        } else {
            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        }

        headerViewHolder.textView.setText(dataSample.get(position).getGroupTitle());

        return convertView;
    }

    class BaseOnClickListener implements View.OnClickListener{

        private final ItemViewHolder holder;
        private final int position;

        public BaseOnClickListener(ItemViewHolder holder, int position){
            this.holder=holder;
            this.position=position;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.add:
                    operationClickListener.operationClickListener(holder.parent,holder.add,position);
                    break;
                case R.id.minus:
                    operationClickListener.operationClickListener(holder.parent,holder.minus,position);
                    break;
            }

        }
    }

    @Override
    public long getHeaderId(int position) {
        return Long.parseLong(dataSample.get(position).getGroupId());
    }

    class ItemViewHolder {
        private ImageView imageView;
        private ImageView add;
        private ImageView minus;
        private TextView count;
        private TextView desc;
        private TextView title;
        private RelativeLayout parent;
    }

    class HeaderViewHolder {
        private TextView textView;
    }

}
