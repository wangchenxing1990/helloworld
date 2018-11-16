package com.technology.waangyukui.permission;

import android.Manifest;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.haoge.easyandroid.EasyAndroid;
import com.haoge.easyandroid.easy.EasyPermissions;
import com.haoge.easyandroid.easy.RationalChain;
import com.technology.waangyukui.mycyclerapp.MyApplication;
import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/6/12.
 */

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonPermission;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        buttonPermission = findViewById(R.id.button_permission);
        buttonPermission.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        EasyPermissions.create(Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_CALENDAR,
//                Manifest.permission.WRITE_CONTACTS)
//                .request(this);
    }
}
