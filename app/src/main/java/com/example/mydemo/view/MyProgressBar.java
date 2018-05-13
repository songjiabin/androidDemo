package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.mydemo.utils.ScreenUtils;

import java.util.logging.MemoryHandler;

/**
 * 重写View实现全新的控件
 */

public class MyProgressBar extends View {

    private float mRadius;
    private int mCircleX, mCircleY;
    private Paint mPaint;

    public MyProgressBar(Context context) {
        super(context);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(context.getResources().getColor(android.R.color.holo_red_dark));

        //初始化 绘制的圆 弧  初始值
        int mWidth = ScreenUtils.getScreenWidth(context);
        int mHight = ScreenUtils.getScreenHeight(context);
        //圆x,y坐标
        mCircleX = mWidth / 2;
        mCircleY = mHight / 2;
        //圆的半径
        mRadius = 400;


    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mCircleX, mCircleY, mRadius, mPaint);
    }
}
