package com.example.mydemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

/**
 * author : 宋佳
 * time   : 2018/08/06
 * desc   :
 * version: 1.0.0
 */

public class MyLoadingView2 extends View implements Animatable, ValueAnimator.AnimatorUpdateListener {
    private RectF outerRectF;
    private RectF innerRectF;
    private Paint mStrokePaint;
    private ValueAnimator mFloatValueAnimator;
    private float mRotateAngle = 0;

    //定义两个不同半径的圆弧  转动不同的角度即可以实现


    public MyLoadingView2(Context context) {
        super(context);
        initView();
    }

    public MyLoadingView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        //定义外矩形
        outerRectF = new RectF();
        //定义内矩形
        innerRectF = new RectF();

        //画笔
        mStrokePaint = new Paint();
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(10f);
        mStrokePaint.setColor(Color.parseColor("#feef50"));
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStrokeCap(Paint.Cap.ROUND);
        mStrokePaint.setStrokeJoin(Paint.Join.ROUND);


        initAnimators();

    }


    private void initAnimators() {
        mFloatValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        mFloatValueAnimator.setRepeatCount(Animation.INFINITE);//循环模式
        mFloatValueAnimator.setDuration(1000);//持续时间
        mFloatValueAnimator.setStartDelay(0);//延迟时间
        mFloatValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;


        //外圆的半径
        float outR = centerX - 10f;
        //内圆的半径
        float inR = (float) (outR * 0.6 - 10f);


        outerRectF.set(centerX - outR, centerY - outR, centerX + outR, centerY + outR);

        innerRectF.set(centerX - inR, centerY - inR, centerX + inR, centerY + inR);


//        canvas.drawRect(outerRectF, mStrokePaint);
//
//        canvas.drawRect(innerRectF, mStrokePaint);


        //画圆弧
        /**
         * 第二个参数： 圆弧是从哪个角度来顺时针绘画的
         * 第三个参数：
         */
        mStrokePaint.setColor(Color.RED);
        //绘制外圆
        canvas.drawArc(outerRectF, mRotateAngle % 360, 270, false, mStrokePaint);
        //绘制内圆
        canvas.drawArc(innerRectF, 270 - mRotateAngle % 360, 270, false, mStrokePaint);


        canvas.save();

    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mRotateAngle = 360 * (float) animation.getAnimatedValue();
        invalidate();
    }

    @Override
    public void start() {
        if (mFloatValueAnimator.isStarted()) {
            return;
        }
        mFloatValueAnimator.addUpdateListener(this);
        mFloatValueAnimator.setRepeatCount(Animation.INFINITE);
        mFloatValueAnimator.start();
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }



    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
//        start();

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        start();
    }
}
