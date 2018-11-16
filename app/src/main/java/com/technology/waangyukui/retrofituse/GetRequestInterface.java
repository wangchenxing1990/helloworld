package com.technology.waangyukui.retrofituse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lenvo on 2018/6/8.
 */

public interface GetRequestInterface {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<TranslationOne> getCall();
}
