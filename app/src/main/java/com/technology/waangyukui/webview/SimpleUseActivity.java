package com.technology.waangyukui.webview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/6/20.
 */

public class SimpleUseActivity extends AppCompatActivity {
    private TextView mTitle, text_beginLoading, text_Loading, text_endLoading;
    private WebView webView1;
    private WebSettings webSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_use);
        mTitle = findViewById(R.id.title);
        text_beginLoading = findViewById(R.id.text_beginLoading);
        text_Loading = findViewById(R.id.text_Loading);
        text_endLoading = findViewById(R.id.text_endLoading);
        webView1 = findViewById(R.id.webView1);

        webSettings = webView1.getSettings();

        webView1.loadUrl("http://www.baidu.com/");

        webView1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl() + "");
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                text_beginLoading.setText("开始加载了");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                text_endLoading.setText("结束加载了");
            }
        });

        webView1.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitle.setText(title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                if (newProgress < 100) {
                    String progress = newProgress + "%";
                    text_beginLoading.setText(progress);
                } else if (newProgress == 100) {
                    String progress = newProgress + "%";
                    text_beginLoading.setText(progress);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView1.canGoBack()) {
            webView1.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (webView1 != null) {
            webView1.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView1.clearHistory();
            ((ViewGroup) webView1.getParent()).removeView(webView1);
            webView1.destroy();
            webView1 = null;
        }
        super.onDestroy();
    }
}
