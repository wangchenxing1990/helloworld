package com.technology.waangyukui.definedView;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.technology.waangyukui.mycyclerapp.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by lenvo on 2018/8/6.
 */
public class DefinedViewOneActivity extends AppCompatActivity {
    private int[] screen;
    private int statusBar;
    private ImageView image_view;
    private Bitmap bitmapOne;
    private DefinedViewOne define_table;
    private File file;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defined_one);



        AllenVersionChecker
                .getInstance()
                .downloadOnly(
                        UIData.create()
                                .setTitle("版本更新")
                                .setContent("1.增加俱乐部人数上限\\n\n" +
                                        "2.优化大菠萝补分暂停逻辑\\n\n" +
                                        "3.修复已知BUG，优化用户体验")
                                .setDownloadUrl("https://cdn.hcapple.com/apps/android-20180727.apk")
                )
                .excuteMission(this);

        define_table = findViewById(R.id.define_table);
        define_table.setDataDisplay(9, 3, 9);
        getContentResolver().registerContentObserver(Settings.System.getUriFor("navigationbar_is_min"), true, mNavigationStatusObserver);

        image_view = findViewById(R.id.image_view);
        screen = getScreenSize(getApplicationContext());
        statusBar = getStatusBarHeight(getApplicationContext());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.icon_image_welcome);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int twoWidth = (bitmap.getWidth() * (screen[1] - statusBar)) / bitmap.getHeight();
        Log.i("获取的图片的宽和高", "获取的图片的宽和高width:::" + width + "  height:::" + height);
        Log.i("获取的图片的宽和高", "手机屏幕的宽和高width:::" + screen[0] + "   height:::" + screen[1]);
        Log.i("获取的图片的宽和高", "手机屏幕的宽和高width:::" + twoWidth + "   height:::" + (screen[1] - statusBar));
        bitmapOne = setImgSize(bitmap, twoWidth, screen[1] - statusBar);
        int newHeight = bitmapOne.getHeight();
        int newWidth = bitmapOne.getWidth();
        Bitmap cutBitmap = Bitmap.createBitmap(bitmapOne, 105, 0, screen[0], screen[1] - statusBar);
        image_view.setImageBitmap(cutBitmap);
        Log.i("获取的图片的宽和高", "缩放后的图片的宽和高width:::" + newWidth + "   height:::" + newHeight);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){ //同意权限申请
//                    makeCall();
                    dowloadNewApp();
                }else { //拒绝权限申请
                    Toast.makeText(this,"权限被拒绝了",Toast.LENGTH_SHORT).show();
                }
                break;

        }

    }

    private ProgressDialog progressDialog;
    private void dowloadNewApp() {
        file = new File(getExternalCacheDir().toString(), "android-20180727.apk");
        Log.i("userr", file + "");
        if (!file.exists()) {
            file.mkdirs();
        } else {
            file.delete();
        }
        try {
            if (file.exists()){
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.download("https://cdn.hcapple.com/apps/android-20180727.apk", file+"", new RequestCallBack<File>() {
            @Override
            public void onStart() {
                super.onStart();
                progressDialog = new ProgressDialog(DefinedViewOneActivity.this);
                progressDialog.setTitle("一网人才");
                progressDialog.setMessage("正在下载...");
                progressDialog.setProgressNumberFormat(" ");
                //可以看到进度的过程，水平的样式
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setCanceledOnTouchOutside(false);
                //一定要显示出来
                progressDialog.show();
            }

            @Override
            public void onSuccess(ResponseInfo<File> responseInfo) {
                Log.i("onSuccess","下载成功");
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }

                Log.i("result",responseInfo.result.length()+"");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                if (Build.VERSION.SDK_INT>=24){
                    Uri apkUri =
                            FileProvider.getUriForFile(DefinedViewOneActivity.this, "com.yiwangrencai.fileprovider", file);
                    intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                }else{
                    Uri fileUri = Uri.fromFile(file);
                    intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
                }
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                //这个就是目标下载文件
//                 File apkFile = responseInfo.result;
//                handler.sendEmptyMessage(190);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (progressDialog != null) {
                    progressDialog.dismiss();
                }
//                Intent intent = new Intent(GuidActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);

//                finish();
            }
        });
    }

    private ContentObserver mNavigationStatusObserver = new ContentObserver(new Handler()) {
        @Override
        public void onChange(boolean selfChange) {
            if (selfChange) {
                Bitmap cutBitmap = Bitmap.createBitmap(bitmapOne, 153, 0, screen[0], screen[1] - statusBar);
                image_view.setImageBitmap(cutBitmap);
            } else {
                Bitmap cutBitmap = Bitmap.createBitmap(bitmapOne, 105, 0, screen[0], screen[1] - statusBar);
                image_view.setImageBitmap(cutBitmap);
            }
            Log.i("yyj", "selfChange = " + selfChange);
            int navigationBarIsMin = Settings.System.getInt(getContentResolver(), "navigationbar_is_min", 0);
        }
    };

    public static int[] getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return new int[]{outMetrics.widthPixels, outMetrics.heightPixels};
    }

    public Bitmap setImgSize(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高.
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例.
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数.
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片.
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
