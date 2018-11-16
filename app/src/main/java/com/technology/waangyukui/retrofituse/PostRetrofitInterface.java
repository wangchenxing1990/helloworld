package com.technology.waangyukui.retrofituse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by lenvo on 2018/6/8.
 */

public interface PostRetrofitInterface {
//    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
//    @POST("api/user/login?client_id=7&client_secret=Y4HkAcva7FOHR7cZg0QLRiTM95Sjyirf78eyRIov")
    @POST("api/user/login")
    @FormUrlEncoded
    Call<TranslationThree> getCallPost(@FieldMap Map<String,Object> map);
}
