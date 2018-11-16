package com.technology.waangyukui.pay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.FormatException;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.technology.waangyukui.mycyclerapp.R;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Hashtable;

/**
 * Created by lenvo on 2018/7/30.
 */

public class WebViewPayActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "";
    private Button button_wechat;
    private Button button_alipay;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_wechat);
        button_wechat = (Button) findViewById(R.id.button_wechat);
        button_alipay = (Button) findViewById(R.id.button_alipay);

        button_wechat.setOnClickListener(this);
        button_alipay.setOnClickListener(this);
//        SharedPreferences sharedPreferences=getSharedPreferences("",P)
    }

    public int IMAGE_CODE = 1;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_wechat:
//                Intent innerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                Intent wrapperIntent = Intent.createChooser(innerIntent, "选择二维码图片");
//                startActivityForResult(wrapperIntent, IMAGE_CODE);
                Intent intentone = new Intent(this, AliAndWeChatPayActivity.class);
                startActivity(intentone);
                break;
            case R.id.button_alipay:
                Intent intentTwo = new Intent(this, AliAndWeChatPayActivity.class);
                startActivity(intentTwo);
                break;
        }
    }



}
