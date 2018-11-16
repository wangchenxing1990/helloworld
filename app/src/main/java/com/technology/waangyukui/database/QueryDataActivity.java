package com.technology.waangyukui.database;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.technology.waangyukui.mycyclerapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenvo on 2018/5/23.
 */
public class QueryDataActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView list_view;
    private MyDataBaseHelper myDataBaseHelper;
    private List<Person> listData = new ArrayList();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_data_activity);
        myDataBaseHelper = new MyDataBaseHelper(getApplicationContext());
        list_view = findViewById(R.id.list_view);
        listData = queryDataResult();//查询数据的返回结果
        MyAdapter adapter = new MyAdapter(getApplicationContext(), listData);
        list_view.setAdapter(adapter);
        list_view.setOnItemClickListener(this);
    }

    /**
     * 查询数据库返回的结果
     */
    private List<Person> queryDataResult() {
        List<Person> list = new ArrayList();
        Cursor cursor = myDataBaseHelper.query();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            String phone = cursor.getString(cursor.getColumnIndex("number"));
            String desc = cursor.getString(cursor.getColumnIndex("desc"));

            Person person = new Person();
            person.setId(id);
            person.setName(name);
            person.setSex(sex);
            person.setPhone(phone);
            person.setDesc(desc);

            list.add(person);

        }

        myDataBaseHelper.close();
        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    class MyAdapter extends BaseAdapter {
        private List<Person> list;
        private LayoutInflater inflater;

        public MyAdapter(Context context, List<Person> list) {
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
            Person person = list.get(i);
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
                viewHolder= (ViewHolder) view.getTag();
            }

            viewHolder.textId.setText(person.getId()+"");
            viewHolder.textName.setText(person.getName()+"");
            viewHolder.textSex.setText(person.getSex()+"");
            viewHolder.textPhone.setText(person.getPhone()+"");
            viewHolder.textDesc.setText(person.getDesc()+"");

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
