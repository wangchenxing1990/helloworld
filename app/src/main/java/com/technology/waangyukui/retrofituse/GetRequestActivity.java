package com.technology.waangyukui.retrofituse;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.technology.waangyukui.mycyclerapp.R;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenvo on 2018/6/8.
 */

public class GetRequestActivity extends AppCompatActivity implements View.OnClickListener {
    private Button retrofitGet;
    private Button retrofitPost;
    private TextView text_get;
    private TextView text_post;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_get);
        retrofitGet = findViewById(R.id.button_retrofit_get);
        retrofitPost = findViewById(R.id.button_retrofit_post);

        text_get = findViewById(R.id.text_get);
        text_post = findViewById(R.id.text_post);
        retrofitGet.setOnClickListener(this);
        retrofitPost.setOnClickListener(this);

    }

    private void getRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRequestInterface requestInterface = retrofit.create(GetRequestInterface.class);
        Call<TranslationOne> call = requestInterface.getCall();
        call.enqueue(new Callback<TranslationOne>() {
            @Override
            public void onResponse(Call<TranslationOne> call, Response<TranslationOne> response) {
                TranslationOne translationOne = response.body();
                text_get.setText(translationOne.getStatus()+"\n"
                        + translationOne.getContent().getVendor()+"\n"
                        + translationOne.getContent().getErrNo()+"\n"
                        + translationOne.getContent().getFrom()+"\n"
                        + translationOne.getContent().getOut()+"\n"
                        + translationOne.getContent().getTo());
            }

            @Override
            public void onFailure(Call<TranslationOne> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_retrofit_get:
                getRequest();
                break;
            case R.id.button_retrofit_post:
                postRequest();
                break;
        }
    }

    private void postRequest() {
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://fanyi.youdao.com/")
                .baseUrl("http://www.bo-qu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map<String, Object> map = new HashMap();
        map.put("username", "wangyukui");
        map.put("password", "123456");
        map.put("client_id", "7");
        map.put("client_secret", "Y4HkAcva7FOHR7cZg0QLRiTM95Sjyirf78eyRIov");
        PostRetrofitInterface translationTwo = retrofit.create(PostRetrofitInterface.class);
        Call<TranslationThree> call = translationTwo.getCallPost(map);
        call.enqueue(new Callback<TranslationThree>() {
            @Override
            public void onResponse(Call<TranslationThree> call, Response<TranslationThree> response) {
                text_post.setText(response.body().getAccessToken() + response.body().getTokenType() + response.body().getExpiresIn());
                System.out.println("翻译是：" + response.body().getAccessToken() + response.body().getTokenType() + response.body().getExpiresIn());
            }

            @Override
            public void onFailure(Call<TranslationThree> call, Throwable t) {

            }
        });
    }
}
