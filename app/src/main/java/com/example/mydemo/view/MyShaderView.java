package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.mydemo.R;

/**
 * time   : 2018/05/31
 * desc   : Shader 学习
 * version: 1.0.0
 */

public class MyShaderView extends View {


    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private Paint mPaint;

    public MyShaderView(Context context) {
        super(context);
        initView(context);
    }

    public MyShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test1);
        //为这个bitmap创建 shader 并制定拉伸的格式
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        mPaint = new Paint();
        //将画笔的 shader 指定为 BitmapShader
        mPaint.setShader(mBitmapShader);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //用带有 BitmapShader的画笔进行 画圆 这样就是一个圆形的 图片了
//        canvas.drawCircle(500, 250, 500, mPaint);


        Paint paint = new Paint();
        //实现一个渐变的效果
        paint.setShader(new LinearGradient(0, 0, 400, 400, Color.BLUE, Color.YELLOW, Shader.TileMode.REPEAT));
        canvas.drawRect(0, 0, 800, 800, paint);


    }
}
