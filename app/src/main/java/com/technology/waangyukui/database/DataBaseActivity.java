package com.technology.waangyukui.database;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.technology.waangyukui.kotlinStudy.bean.ChoiceBean;
import com.technology.waangyukui.mycyclerapp.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenvo on 2018/5/22.
 */

public class DataBaseActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_name;
    private EditText edit_phone;
    private EditText edit_desc;

    private CheckBox check_boy;
    private CheckBox check_girl;

    private Button button_insert;
    private Button button_query;
    private Button button_query_goods;
    private Button button_insert_good;
    private String sexText = "男";
    private MyDataBaseHelper myDataBaseHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        myDataBaseHelper = new MyDataBaseHelper(getApplicationContext());
        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);
        edit_desc = findViewById(R.id.edit_desc);

        check_boy = findViewById(R.id.check_boy);
        check_girl = findViewById(R.id.check_girl);

        button_insert = findViewById(R.id.button_insert);
        button_query = findViewById(R.id.button_query);
        button_query_goods = findViewById(R.id.button_query_goods);
        button_insert_good = findViewById(R.id.button_insert_good);

        check_boy.setOnClickListener(this);
        check_girl.setOnClickListener(this);
        button_insert.setOnClickListener(this);
        button_query.setOnClickListener(this);
        button_query_goods.setOnClickListener(this);
        button_insert_good.setOnClickListener(this);
        check_boy.setChecked(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.check_boy:
                if (check_girl.isChecked()) {
                    check_girl.setChecked(false);
                    sexText = "男";
                }
                break;
            case R.id.check_girl:
                if (check_boy.isChecked()) {
                    check_boy.setChecked(false);
                    sexText = "女";
                }
                break;
            case R.id.button_insert:
                inserData();//插入数据
                break;
            case R.id.button_query:
                Intent intent = new Intent(this, QueryDataActivity.class);
                startActivity(intent);
                //    queryData();//查询数据
                break;
            case R.id.button_query_goods:
                Intent intentGoods = new Intent(getApplicationContext(), QueryGoodsDataActivity.class);
                startActivity(intentGoods);
                break;
            case R.id.button_insert_good:
                insertDataGood();
                break;
        }
    }

    private void insertDataGood() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url("https://www.xunquan.shop/api/v2.0/xunquan/cat/0?page=1")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
                Gson gson = new Gson();
                GoodBean choiceBean = gson.fromJson(str, GoodBean.class);
                Message message = new Message();
                message.obj = choiceBean;
                handler.sendMessage(message);
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            GoodBean choiceBean = (GoodBean) msg.obj;
            for (int i = 0; i < choiceBean.getData().size(); i++) {
                ContentValues values = new ContentValues();
                values.put("shopName",choiceBean.getData().get(i).getShopName());
                values.put("image",choiceBean.getData().get(i).getImage());
                values.put("taobaoId",choiceBean.getData().get(i).getTaobaoId());
                values.put("couponPromotUrl",choiceBean.getData().get(i).getCouponPromotUrl());
                values.put("sellCount",choiceBean.getData().get(i).getSellCount());
                values.put("goodsName",choiceBean.getData().get(i).getGoodsName());
                values.put("endTime",choiceBean.getData().get(i).getEndTime());
                values.put("cateId",choiceBean.getData().get(i).getCateId());
                values.put("price",choiceBean.getData().get(i).getPrice());
                values.put("couponDenomination",choiceBean.getData().get(i).getCouponDenomination());
                myDataBaseHelper.insertGoods(values);
            }
        }
    };

    /**
     * 查询数据
     */
    private void queryData() {
        Cursor cursor = myDataBaseHelper.query();
        Log.i("cursor", cursor + "");
    }

    private void inserData() {
        String name = edit_name.getText().toString().trim();
        String phone = edit_phone.getText().toString().trim();
        String desc = edit_desc.getText().toString().trim();

        if (name == null || TextUtils.isEmpty(name)) {
            Toast.makeText(this, "姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone == null || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (desc == null || TextUtils.isEmpty(desc)) {
            Toast.makeText(this, "备注不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("sex", sexText);
        values.put("number", phone);
        values.put("desc", desc);
        myDataBaseHelper.insert(values);

    }
}
