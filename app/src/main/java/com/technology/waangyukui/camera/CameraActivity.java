package com.technology.waangyukui.camera;


import android.content.Context;

import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.TextureView;

import com.technology.waangyukui.mycyclerapp.R;


import static com.technology.waangyukui.mycyclerapp.MyApplication.context;

/**
 * Created by lenvo on 2018/6/14.
 */

public class CameraActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_camera);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,Camera2BasicFragment.Instance()).commit();
    }
}
