package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/05/30
 * desc   :  PorterDuffXfermode 的使用  将两个图层进行交互的帮助类
 * version: 1.0.0
 */

public class MyPorterDuffXfermodeView extends View {


    private Bitmap mBitmap;
    private Bitmap mOut;
    private Paint mPaint;

    public MyPorterDuffXfermodeView(Context context) {
        super(context);
        initView(context);
    }

    public MyPorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyPorterDuffXfermodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        //外面的遮罩层
        mOut = mBitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);

        //遮罩层的大小的画布
        Canvas canvas1 = new Canvas(mOut);

        //创建 椭圆
        canvas1.drawRoundRect(0, 0, mBitmap.getWidth(), mBitmap.getHeight(), 60, 60, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //用 带有 PorterDuffXfermode的画笔画将 图片 画在  在遮罩层上
        canvas1.drawBitmap(mBitmap, 0, 0, mPaint);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {

        //在画布上画遮罩层
        canvas.drawBitmap(mOut, 0, 0, null);

    }
}
