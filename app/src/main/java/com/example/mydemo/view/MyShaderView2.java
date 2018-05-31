package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.mydemo.R;

/**
 * time   : 2018/05/31
 * desc   : Shader 学习
 * version: 1.0.0
 */

public class MyShaderView2 extends View {


    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private Paint mPaint;
    private Bitmap mRefBitmap;

    public MyShaderView2(Context context) {
        super(context);
        initView(context);
    }

    public MyShaderView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyShaderView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        // 通过Matrix 来实现图片的垂直反转
        Matrix matrix = new Matrix();
        matrix.setScale(1F, -1F);
        mRefBitmap =Bitmap.createBitmap(mBitmap, 0, 0, mBitmap.getWidth(), mBitmap.getHeight(), matrix, true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(mRefBitmap,0,0,null);

    }
}
