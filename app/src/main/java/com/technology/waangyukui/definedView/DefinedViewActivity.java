package com.technology.waangyukui.definedView;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.technology.waangyukui.mycyclerapp.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import okhttp3.Call;

/**
 * Created by lenvo on 2018/8/6.
 */

public class DefinedViewActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_one;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defined_view);

        button_one = findViewById(R.id.button_one);

        button_one.setOnClickListener(this);
//        downloadFile2();
        downLoadNewApp();
    }
    private void downloadFile2(){
        //下载路径，如果路径无效了，可换成你的下载路径
//        String url = "http://c.qijingonline.com/test.mkv";
        String url = "https://cdn.hcapple.com/apps/android-20180727.apk";
        //创建下载任务,downloadUrl就是下载链接
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //指定下载路径和下载文件名
        request.setDestinationInExternalPublicDir("", url.substring(url.lastIndexOf("/") + 1));
        //获取下载管理器
        DownloadManager downloadManager= (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载任务加入下载队列，否则不会进行下载
        downloadManager.enqueue(request);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.setDataAndType(Uri.fromFile(url.substring(url.lastIndexOf("/") + 1)));
//                "application/vnd.android.package-archive");
        startActivityForResult(intent, 0);
    }



    private void downLoadNewApp() {

//        if (StringUtils.isEmpty(version.getUrl())) {
//            ToastUtils.showToast("新版本的APP url为空");
//            enterLoginActivity();
//            return;
//        }

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("正在下载");
        dialog.setMessage("");
        dialog.setCancelable(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();

//        if (!NetUtils.hasNet(SplashActivity.this)) {
//            ToastUtils.showToast("请检查网络环境");
//            return;
//        }

//        System.out.println("SplashActivity.downLoadNewApp=" + version.getUrl());

        OkHttpUtils
                .get()
                .url("https://cdn.hcapple.com/apps/android-20180727.apk")
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(), "android-20180727.apk") {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        enterLoginActivity();
                        System.out.println("SplashActivity.onError=哈哈哈");
//                        ToastUtils.showToast("下载更新包失败");
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
//                        super.inProgress(progress, total, id);
                        dialog.setProgress((int) (100 * progress));
                    }

                    @Override
                    public void onResponse(File response, int id) {

                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        if (Build.VERSION.SDK_INT>=24){
                            Uri apkUri =
                                    FileProvider.getUriForFile(DefinedViewActivity.this, "com.technology.waangyukui.mycyclerapp.fileprovider", response);
                            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                        }else{
                            Uri fileUri = Uri.fromFile(response);
                            intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
                        }
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

//                        Intent intent = new Intent();
//                        intent.setAction(Intent.ACTION_VIEW);
//                        intent.addCategory(Intent.CATEGORY_DEFAULT);
//                        intent.setDataAndType(Uri.fromFile(response),
//                                "application/vnd.android.package-archive");
////                        startActivityForResult(intent, 0);
//                        startActivity(intent);
                    }
                });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_one:
                Intent intentOne = new Intent(getApplicationContext(),DefinedViewOneActivity.class);
                startActivity(intentOne);
                break;
//            case R.id.button_two:
//                break;
//            case R.id.button_three:
//                break;
        }
    }
}
