package com.technology.waangyukui.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.technology.waangyukui.mycyclerapp.MyApplication;
import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/6/20.
 */

public class JavaScriptActivity extends AppCompatActivity {
    private WebView web_view_three;
    private WebSettings webSettings;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_script);

        web_view_three=findViewById(R.id.web_view_three);
        webSettings = web_view_three.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web_view_three.loadUrl("file:///android_asset/javaScript.html");
        web_view_three.addJavascriptInterface(JavaScriptActivity.this,"test");

    }

    @JavascriptInterface
    public void hello(String message){
        Toast.makeText(MyApplication.context,message,Toast.LENGTH_SHORT).show();
    }
}
