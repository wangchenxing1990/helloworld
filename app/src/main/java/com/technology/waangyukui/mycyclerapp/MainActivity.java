package com.technology.waangyukui.mycyclerapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.technology.waangyukui.bluetooth.BlueTeethActivity;
import com.technology.waangyukui.broadcast.MyBroadcastReceiver;
import com.technology.waangyukui.broadcast.ReceiverActivity;
import com.technology.waangyukui.camera.CameraActivity;
import com.technology.waangyukui.canvas.BezierActivity;
import com.technology.waangyukui.canvas.CanvasActivity;
import com.technology.waangyukui.canvas.PathActivity;
import com.technology.waangyukui.database.DataBaseActivity;
import com.technology.waangyukui.defineEditText.DefineEditTextActivity;
import com.technology.waangyukui.definedView.DefinedViewActivity;
import com.technology.waangyukui.elema.ElemaActivity;
import com.technology.waangyukui.kotlinStudy.MainTwoActivity;
import com.technology.waangyukui.layout.LayoutActivity;
import com.technology.waangyukui.observer.ObserverActivity;
import com.technology.waangyukui.pay.WebViewPayActivity;
import com.technology.waangyukui.permission.PermissionActivity;
import com.technology.waangyukui.picture.ActivityPicture;
import com.technology.waangyukui.retrofituse.GetRequestActivity;
import com.technology.waangyukui.rx.RXJavaActivity;
import com.technology.waangyukui.service.ServiceActivity;
import com.technology.waangyukui.suspendcart.SuspendCarActivity;
import com.technology.waangyukui.toast.ToastActivity;
import com.technology.waangyukui.webview.WebViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text_view;
    private Button button, button_buy_car, button_e_le_ma, button_picture, button_statue, button_layout, button_shortcut,
            button_app_edit_text, button_defined_toast, button_display_observer, button_retrofit_user, button_start_service,
            button_easy_permission, button_study_kotlin, button_camera, button_use_web_view, button_canvas_use, button_path_use,
            button_bezier, button_rx_java, button_web_view, button_defined_view, broadcast_receiver;

    private final ContentObserver mGpsMonitor = new ContentObserver(null) {
        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            mLocationManager = (LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            boolean enabled = mLocationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (!enabled) {
//                Toast.makeText(MainActivity.this, "位置信息没有打开", Toast.LENGTH_SHORT).show();
                showDialog();
                System.out.println("gps enabled? 位置信息没有打开" + enabled);
            } else {
//                Toast.makeText(MainActivity.this, "位置信息已经打开", Toast.LENGTH_SHORT).show();
                System.out.println("gps enabled? 位置信息已经打开 " + enabled);
            }

        }
    };

    private void showDialog() {

    }

    LocationManager mLocationManager;
    MyBroadcastReceiver myBroadcastReceiver;
    private static final String BROADCAST_ACTION = "com.wangyukui";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTION);
        registerReceiver(myBroadcastReceiver, intentFilter);

        text_view = findViewById(R.id.text_view);
        button = findViewById(R.id.button);
        button_buy_car = findViewById(R.id.button_buy_car);
        button_e_le_ma = findViewById(R.id.button_e_le_ma);
        button_picture = findViewById(R.id.button_picture);
        button_statue = findViewById(R.id.button_statue);
        button_layout = findViewById(R.id.button_layout);
        button_shortcut = findViewById(R.id.button_shortcut);
        button_app_edit_text = findViewById(R.id.button_app_edit_text);
        button_defined_toast = findViewById(R.id.button_defined_toast);
        button_display_observer = findViewById(R.id.button_display_observer);
        button_retrofit_user = findViewById(R.id.button_retrofit_user);
        button_start_service = findViewById(R.id.button_start_service);
        button_easy_permission = findViewById(R.id.button_easy_permission);
        button_study_kotlin = findViewById(R.id.button_study_kotlin);
        button_camera = findViewById(R.id.button_camera);
        button_use_web_view = findViewById(R.id.button_use_web_view);
        button_canvas_use = findViewById(R.id.button_canvas_use);
        button_path_use = findViewById(R.id.button_path_use);
        button_bezier = findViewById(R.id.button_bezier);
        button_rx_java = findViewById(R.id.button_rx_java);
        button_web_view = findViewById(R.id.button_web_view);
        button_defined_view = findViewById(R.id.button_defined_view);
        broadcast_receiver = findViewById(R.id.broadcast_receiver);

        button.setOnClickListener(this);
        button_buy_car.setOnClickListener(this);
        button_e_le_ma.setOnClickListener(this);
        button_picture.setOnClickListener(this);
        button_statue.setOnClickListener(this);
        button_layout.setOnClickListener(this);
        button_shortcut.setOnClickListener(this);
        button_app_edit_text.setOnClickListener(this);
        button_defined_toast.setOnClickListener(this);
        button_display_observer.setOnClickListener(this);
        button_retrofit_user.setOnClickListener(this);
        button_start_service.setOnClickListener(this);
        button_easy_permission.setOnClickListener(this);
        button_study_kotlin.setOnClickListener(this);
        button_camera.setOnClickListener(this);
        button_use_web_view.setOnClickListener(this);
        button_canvas_use.setOnClickListener(this);
        button_path_use.setOnClickListener(this);
        button_bezier.setOnClickListener(this);
        button_rx_java.setOnClickListener(this);
        button_web_view.setOnClickListener(this);
        button_defined_view.setOnClickListener(this);
        broadcast_receiver.setOnClickListener(this);

        // text_view.setMovementMethod(new ScrollingMovementMethod());

    }

    @Override
    protected void onStart() {
        super.onStart();
        getContentResolver()
                .registerContentObserver(
                        Settings.Secure
                                .getUriFor(Settings.System.LOCATION_PROVIDERS_ALLOWED),
                        false, mGpsMonitor);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getContentResolver().unregisterContentObserver(mGpsMonitor);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(myBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button://数据库的使用
                Intent intent = new Intent(this, DataBaseActivity.class);
                startActivity(intent);
                break;
            case R.id.button_buy_car://购物车的隐藏
                Intent intentCar = new Intent(getApplicationContext(), SuspendCarActivity.class);
                startActivity(intentCar);
                break;
            case R.id.button_e_le_ma://仿照饿了么的购物车
                Intent intentElema = new Intent(getApplicationContext(), ElemaActivity.class);
                startActivity(intentElema);
                break;
            case R.id.button_picture://图片的简单使用
                Intent intentPicture = new Intent(getApplicationContext(), ActivityPicture.class);
                startActivity(intentPicture);
                break;
            case R.id.button_statue://
                Intent intentStatue = new Intent(getApplicationContext(), ActivityStatue.class);
                startActivity(intentStatue);
                break;
            case R.id.button_layout:
                Intent intentLayout = new Intent(getApplicationContext(), LayoutActivity.class);
                startActivity(intentLayout);
                break;
            case R.id.button_shortcut:
                Intent intentShort = new Intent(getApplicationContext(), BlueTeethActivity.class);
                startActivity(intentShort);
                break;
            case R.id.button_app_edit_text://自定义的EditText
                Intent intentAppEditText = new Intent(getApplicationContext(), DefineEditTextActivity.class);
                startActivity(intentAppEditText);
                break;
            case R.id.button_defined_toast://自定义的toast
                Intent intentToast = new Intent(getApplicationContext(), ToastActivity.class);
                startActivity(intentToast);
                break;
            case R.id.button_display_observer://EventBus的使用
                Intent intentObserver = new Intent(getApplicationContext(), ObserverActivity.class);
                startActivity(intentObserver);
                break;
            case R.id.button_retrofit_user:
                Intent intentRetrofit = new Intent(getApplicationContext(), GetRequestActivity.class);
                startActivity(intentRetrofit);
                break;
            case R.id.button_start_service://启动服务模拟下载任务
                Intent intentService = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intentService);
                break;
            case R.id.button_easy_permission://EasyPermission获取权限
                Intent intentPermission = new Intent(getApplicationContext(), PermissionActivity.class);
                startActivity(intentPermission);
                break;
            case R.id.button_study_kotlin://学习kotlin
                Intent intentKotlin = new Intent(getApplicationContext(), MainTwoActivity.class);
                startActivity(intentKotlin);
                break;
            case R.id.button_camera://自定义相机
                Intent intentCamera = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intentCamera);
                break;
            case R.id.button_use_web_view://webView的使用
                Intent intentWebView = new Intent(getApplicationContext(), WebViewActivity.class);
                startActivity(intentWebView);
                break;
            case R.id.button_canvas_use:
                Intent intentCanvas = new Intent(getApplicationContext(), CanvasActivity.class);
                startActivity(intentCanvas);
                break;
            case R.id.button_path_use:
                Intent intentPath = new Intent(getApplicationContext(), PathActivity.class);
                startActivity(intentPath);
                break;
            case R.id.button_bezier:
                Intent intentBezier = new Intent(getApplicationContext(), BezierActivity.class);
                startActivity(intentBezier);
                break;
            case R.id.button_rx_java:
                Intent intentRXJava = new Intent(getApplicationContext(), RXJavaActivity.class);
                startActivity(intentRXJava);
                break;
            case R.id.button_web_view:
                Intent intentWebViewPay = new Intent(getApplicationContext(), WebViewPayActivity.class);
                startActivity(intentWebViewPay);
                break;
            case R.id.button_defined_view:
                Intent intentDefinedView = new Intent(getApplicationContext(), DefinedViewActivity.class);
                startActivity(intentDefinedView);
                break;
            case R.id.broadcast_receiver:
                Intent intentReceiver = new Intent(getApplicationContext(), ReceiverActivity.class);
                startActivity(intentReceiver);
                break;
        }
    }

}
