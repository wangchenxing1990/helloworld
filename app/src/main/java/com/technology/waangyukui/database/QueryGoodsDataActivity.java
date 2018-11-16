package com.technology.waangyukui.database;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.technology.waangyukui.mycyclerapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenvo on 2018/6/15.
 */
public class QueryGoodsDataActivity extends AppCompatActivity {
    private ListView listView;
    private MyDataBaseHelper myDataBaseHelper;
    private List<GoodBeanTwo> listData = new ArrayList();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_data_activity);
        listView = findViewById(R.id.list_view);
        myDataBaseHelper = new MyDataBaseHelper(getApplicationContext());
        Cursor cursor = myDataBaseHelper.queryGoods();

        while (cursor.moveToNext()) {
            String shopName = cursor.getString(cursor.getColumnIndex("shopName"));
            String image = cursor.getString(cursor.getColumnIndex("image"));
            String taobaoId = cursor.getString(cursor.getColumnIndex("taobaoId"));
            String couponPromotUrl = cursor.getString(cursor.getColumnIndex("couponPromotUrl"));
            String sellCount = cursor.getString(cursor.getColumnIndex("sellCount"));
            String goodsName = cursor.getString(cursor.getColumnIndex("goodsName"));
            String endTime = cursor.getString(cursor.getColumnIndex("endTime"));
            String cateId = cursor.getString(cursor.getColumnIndex("cateId"));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String couponDenomination = cursor.getString(cursor.getColumnIndex("couponDenomination"));

            GoodBeanTwo goodBean = new GoodBeanTwo();
            goodBean.setShopName(shopName);
            goodBean.setImage(image);
            goodBean.setTaobaoId(taobaoId);
            goodBean.setCouponPromotUrl(couponPromotUrl);
            goodBean.setSellCount(sellCount);
            goodBean.setGoodsName(goodsName);
            goodBean.setEndTime(endTime);
            goodBean.setCateId(cateId);
            goodBean.setPrice(price);
            goodBean.setCouponDenomination(couponDenomination);

            listData.add(goodBean);

        }


        MyAdapter adapter = new MyAdapter(getApplicationContext(), listData);
        listView.setAdapter(adapter);

    }


    class MyAdapter extends BaseAdapter {
        private List<GoodBeanTwo> list;
        private LayoutInflater inflater;

        public MyAdapter(Context context, List<GoodBeanTwo> list) {
            inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            GoodBeanTwo person = list.get(i);
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = inflater.inflate(R.layout.item_list, null);
                viewHolder.textId = view.findViewById(R.id.text_id);
                viewHolder.textName = view.findViewById(R.id.text_name);
                viewHolder.textSex = view.findViewById(R.id.text_sex);
                viewHolder.textPhone = view.findViewById(R.id.text_phone);
                viewHolder.textDesc = view.findViewById(R.id.text_desc);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

               viewHolder.textId.setText(person.getGoodsName() + "");
               viewHolder.textName.setText(person.getShopName() + "");
               viewHolder.textSex.setText(person.getPrice() + "");
               viewHolder.textPhone.setText(person.getSellCount() + "");
               viewHolder.textDesc.setText(person.getImage() + "");

            return view;

        }

        class ViewHolder {
            private TextView textId;
            private TextView textName;
            private TextView textSex;
            private TextView textPhone;
            private TextView textDesc;
        }
    }


}
