package com.technology.waangyukui.retrofituse;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenvo on 2018/6/8.
 */

public class TranslationThree {

    /**
     * token_type : Bearer
     * expires_in : 31536000
     * access_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjhkZmRiYmQwOWEyNzE5MzQ0YTBkMGYzNzIyZDdmMjNhMGY5OTM1Y2M1OTY1ZTEzODY4NzU1ZThhYTllOWYyZDhhZTFjZmFjYWVkODBkODk0In0.eyJhdWQiOiI3IiwianRpIjoiOGRmZGJiZDA5YTI3MTkzNDRhMGQwZjM3MjJkN2YyM2EwZjk5MzVjYzU5NjVlMTM4Njg3NTVlOGFhOWU5ZjJkOGFlMWNmYWNhZWQ4MGQ4OTQiLCJpYXQiOjE1Mjg0MzgyNDMsIm5iZiI6MTUyODQzODI0MywiZXhwIjoxNTU5OTc0MjQzLCJzdWIiOiIxMTg0Iiwic2NvcGVzIjpbIioiXX0.C1DZK_O6hnwlzxKPGmpy3WY6NEPrkACQv-tUOFHGNr8JTRoubQS20RF74KJopJl8XLHx6d5-vgoJGp02uEBt
     */

    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("access_token")
    private String accessToken;

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
