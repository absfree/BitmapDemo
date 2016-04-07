package com.yxy.zlp.bitmapdemo;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private BitmapFactory.Options mOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.iv);

        Resources mRes = getResources();
        //获取原始宽高，并存储在options对象的outWidth和outHeight实例域中
        mOptions = new BitmapFactory.Options();
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(mRes, R.drawable.image, mOptions);

        mOptions.inSampleSize = calSampleSize(mOptions, 100, 100);
        mOptions.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(mRes, R.drawable.image, mOptions);
        imageView.setImageBitmap(bitmap);
    }

    //dstWidth和dstHeight分别为目标ImageView的宽高
    public int calSampleSize(BitmapFactory.Options options, int dstWidth, int dstHeight) {
        int rawWidth = options.outWidth;
        int rawHeight = options.outHeight;
        int inSampleSize = 1;
        if (rawWidth > dstWidth || rawHeight > dstHeight) {
            float ratioHeight = (float) rawHeight / dstHeight;
            float ratioWidth = (float) rawWidth / dstHeight;
            inSampleSize = (int) Math.min(ratioWidth, ratioHeight);
        }
        return inSampleSize;
    }

}
