package com.technology.waangyukui.webview;

import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.technology.waangyukui.mycyclerapp.MyApplication;

/**
 * Created by lenvo on 2018/6/20.
 */

public class AndroidObject extends Object {

    @JavascriptInterface
    public void hello(String message){
        Toast.makeText(MyApplication.context,message,Toast.LENGTH_SHORT).show();
    }

}
