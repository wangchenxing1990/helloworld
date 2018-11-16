package com.technology.waangyukui.webview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/6/20.
 */

public class JavaAndroidActivity extends AppCompatActivity {
    private WebView webView;
    private WebSettings webSettings;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_android);
        webView = findViewById(R.id.web_view_two);
        button = findViewById(R.id.button_java);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.loadUrl("file:///android_asset/javaScript.html");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.post(new Runnable() {
                    @Override
                    public void run() {
                        if (Build.VERSION.SDK_INT < 18) {
                            webView.loadUrl("javaScript:callJS()");
                        } else {
                            webView.evaluateJavascript("javaScript:callJS()", new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String s) {

                                }
                            });
                        }
                    }
                });
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(JavaAndroidActivity.this);
                alertDialog.setTitle("Alert");
                alertDialog.setMessage(message);
                alertDialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        result.confirm();
                    }
                });
                alertDialog.setCancelable(false);
                alertDialog.create().show();
                return true;
            }
        });
    }
}
