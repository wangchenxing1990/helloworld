package com.technology.waangyukui.pay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by lenvo on 2018/7/30.
 */

public class WebViewContent extends WebView {
    private Context context;
    public WebViewContent(Context context) {
        super(context);
        this.context=context;
        initView();
    }

    public WebViewContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        WebSettings webSettings=getSettings();
        webSettings.setSupportZoom(false);
        webSettings.setSavePassword(false);

        webSettings.setSaveFormData(false);
        webSettings.setJavaScriptEnabled(true);

        webSettings.setBuiltInZoomControls(false);
        webSettings.setLoadWithOverviewMode(true);  //是否使用WebView加载页面,也就是说,镜头拉出宽度适合在屏幕上的内容。
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setPluginState(WebSettings.PluginState.ON);
        //H5
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
//        setWebViewClient(new MyWebViewClient());
//        setWebChromeClient(new WebChromeClient());
//        loadUrl(url);
    }

    public void setWebViewClient(WebViewClient client){
        if (client!=null){
            setWebViewClient(client);
        }
    }

    public void setWebChromeClient(WebChromeClient webChromeClient){
        if (webChromeClient!=null){
            setWebChromeClient(new WebChromeClient());
        }
    }
}
