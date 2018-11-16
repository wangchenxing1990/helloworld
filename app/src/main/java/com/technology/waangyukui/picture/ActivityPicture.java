package com.technology.waangyukui.picture;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.technology.waangyukui.mycyclerapp.R;

/**
 * Created by lenvo on 2018/5/29.
 */

public class ActivityPicture extends AppCompatActivity {
    private ImageView image_view_one;
    private ImageView image_view_two;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        image_view_one = findViewById(R.id.image_view_one);
        image_view_two = findViewById(R.id.image_view_two);

        Drawable drawable = getWallpaper();
        Bitmap bitmap = ImageUtils.drawableToBitmap(drawable);
        Bitmap zoomBitmap=ImageUtils.zoomBitmap(bitmap,300,300);
        Bitmap roundCornBitmap=ImageUtils.getRoundedCorneredBitmap(zoomBitmap,20.0f);
        Bitmap reflectionBitmapOrigin=ImageUtils.createReflectionWithImageOrigin(zoomBitmap);
        image_view_two.setImageBitmap(reflectionBitmapOrigin);
        image_view_one.setImageBitmap(roundCornBitmap);

    }
}
