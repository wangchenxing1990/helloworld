package com.technology.waangyukui.webview;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/6/20.
 */

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_use_simple,button_java_and_android,button_java_script;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        button_use_simple=findViewById(R.id.button_use_simple);
        button_java_and_android=findViewById(R.id.button_java_and_android);
        button_java_script=findViewById(R.id.button_java_script);
        button_use_simple.setOnClickListener(this);
        button_java_and_android.setOnClickListener(this);
        button_java_script.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_use_simple:
                Intent intentSimpleUse=new Intent(getApplicationContext(),SimpleUseActivity.class);
                startActivity(intentSimpleUse);
                break;
            case R.id.button_java_and_android:
                Intent intentJavaAndroid=new Intent(getApplicationContext(),JavaAndroidActivity.class);
                startActivity(intentJavaAndroid);
                break;
            case R.id.button_java_script:
                Intent intentJavaScript=new Intent(getApplicationContext(),JavaScriptActivity.class);
                startActivity(intentJavaScript);
                break;
        }
    }
}
