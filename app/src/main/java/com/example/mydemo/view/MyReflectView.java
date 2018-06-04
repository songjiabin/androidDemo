package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.mydemo.R;

public class MyReflectView extends View {
    private Bitmap mSrcBitmap, mRefBitmap;
    private Paint mPaint;
    private PorterDuffXfermode mXfermode;

    public MyReflectView(Context context) {
        super(context);
        initRes(context);
    }

    public MyReflectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initRes(context);
    }

    public MyReflectView(Context context, AttributeSet attrs,
                         int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initRes(context);
    }

    private void initRes(Context context) {
        mSrcBitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.test1);
        Matrix matrix = new Matrix();
        // 通过Matrix 来实现图片的垂直反转
        matrix.setScale(1F, -1F);
        //创建新的 bitmap
        mRefBitmap = Bitmap.createBitmap(mSrcBitmap, 0, 0,
                mSrcBitmap.getWidth(), mSrcBitmap.getHeight(), matrix, true);

        mPaint = new Paint();
        mPaint.setShader(new LinearGradient(0, mSrcBitmap.getHeight(), 0,
                mSrcBitmap.getHeight() + mSrcBitmap.getHeight() / 4,
                0XDD000000, 0X10000000, Shader.TileMode.CLAMP));
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(mSrcBitmap, 0, 0, null);
        canvas.drawBitmap(mRefBitmap, 0, mSrcBitmap.getHeight(), null);
        mPaint.setXfermode(mXfermode);
        // 绘制渐变效果矩形
        canvas.drawRect(0, mSrcBitmap.getHeight(),
                mRefBitmap.getWidth(), mSrcBitmap.getHeight() * 2, mPaint);
        mPaint.setXfermode(null);
    }
}