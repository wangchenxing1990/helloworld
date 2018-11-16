package com.technology.waangyukui.picture;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;

import java.text.BreakIterator;

/**
 * Created by lenvo on 2018/5/29.
 */

public class ImageUtils {
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scancelWidth = (float) w / width;
        float scancelHeight = (float) h / height;
        matrix.postScale(scancelWidth, scancelHeight);
        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newBitmap;
    }

    public static Bitmap getRoundedCorneredBitmap(Bitmap bitmap, float roundPx) {

        final int color = 0xff424242;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap createReflectionWithImageOrigin(Bitmap bitmap) {
        final int reflectionGap = 4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        Bitmap reflectionBitmap = Bitmap.createBitmap(bitmap, 0, height/2, width, height / 2, matrix, false);
        Bitmap bitmapWithReflection = Bitmap.createBitmap(width, height + height / 2, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap,0,0,null);

        Paint deafalePaint=new Paint();
        canvas.drawRect(0,height,width,height+reflectionGap,deafalePaint);
        canvas.drawBitmap(reflectionBitmap,0,height+reflectionGap,null);

        Paint paint=new Paint();
        LinearGradient linearGradient=new LinearGradient(0,bitmap.getHeight(),
                0,bitmapWithReflection.getHeight()+reflectionGap,0x70ffffff,0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(linearGradient);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0,height,width,bitmapWithReflection.getHeight()+reflectionGap,paint);

        return bitmapWithReflection;
    }
}
