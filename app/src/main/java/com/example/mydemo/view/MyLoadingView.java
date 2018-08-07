package com.example.mydemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/07/30
 * desc   : 自定义的loading效果 View
 * version: 1.0.0
 */

public class MyLoadingView extends View implements Animatable, ValueAnimator.AnimatorUpdateListener {

    private Context context;

    private static final long ANIMATION_START_DELAY = 200;
    private static final long ANIMATION_DURATION = 1000;
    private static final int OUTER_CIRCLE_ANGLE = 270;
    private static final int INTER_CIRCLE_ANGLE = 90;

    private int color;
    //外圆
    private RectF mOuterCircleRectF;
    //内圆
    private RectF mInnerCircleRectF;
    private float lineWidth;
    private ValueAnimator mFloatValueAnimator;
    private Paint mStrokePaint;
    private float mRotateAngle;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyLoadingView(Context context) {
        super(context);
        initView(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initView(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyLoadingViewStyle);
        //线宽
        lineWidth = typedArray.getFloat(R.styleable.MyLoadingViewStyle_lineWidth, 10.0f);
        color = typedArray.getColor(R.styleable.MyLoadingViewStyle_viewColor, context.getColor(R.color.colorYellow));
        typedArray.recycle();
        initAnimators();

        //外圆
        mOuterCircleRectF = new RectF();
        //内圆
        mInnerCircleRectF = new RectF();
        //初始化画笔
        initPaint(lineWidth, color);
        //旋转角度
        mRotateAngle = 0;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取View的中心
        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;


        if (lineWidth > centerX) {
            throw new IllegalArgumentException("line宽度太大了...");
        }


        //外圆半径，因为我们的弧形是有宽度的，所以计算半径的时候应该把这部分减去，不然会有切割的效果
        float outR = centerX - lineWidth;


        //小圆半径
        float inR = outR * 0.6f - lineWidth;


        //设置弧形的距离上下左右的距离，也就是包围园的矩形。
        mOuterCircleRectF.set(centerX - outR, centerY - outR, centerX + outR, centerY + outR);
        mInnerCircleRectF.set(centerX - inR, centerY - inR, centerX + inR, centerY + inR);



        //绘制外圆
        canvas.drawArc(mOuterCircleRectF, mRotateAngle % 360, OUTER_CIRCLE_ANGLE, false, mStrokePaint);
        //绘制内圆
        canvas.drawArc(mInnerCircleRectF, 270 - mRotateAngle % 360, INTER_CIRCLE_ANGLE, false, mStrokePaint);


        //恢复画板的状态
        canvas.save();
    }


    private void initAnimators() {
        mFloatValueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        mFloatValueAnimator.setRepeatCount(Animation.INFINITE);//循环模式
        mFloatValueAnimator.setDuration(ANIMATION_DURATION);//持续时间
        mFloatValueAnimator.setStartDelay(ANIMATION_START_DELAY);//延迟时间
        mFloatValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    /**
     * 初始化画笔
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initPaint(float lineWidth, int color) {
        mStrokePaint = new Paint();
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setStrokeWidth(lineWidth);
        mStrokePaint.setColor(color);
        mStrokePaint.setAntiAlias(true);
        mStrokePaint.setStrokeCap(Paint.Cap.ROUND);
        mStrokePaint.setStrokeJoin(Paint.Join.ROUND);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startLoading();
    }

    public void startLoading() {
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLoading();
    }

    public void stopLoading() {
        stop();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mRotateAngle = 360 * (float) animation.getAnimatedValue();
        invalidate();
    }

    protected void computeUpdateValue(float animatedValue) {
        mRotateAngle = (int) (360 * animatedValue);
    }

    @Override
    public void start() {
        if (mFloatValueAnimator.isStarted()) {
            return;
        }
        mFloatValueAnimator.addUpdateListener(this);
        mFloatValueAnimator.setRepeatCount(Animation.INFINITE);
        mFloatValueAnimator.setDuration(ANIMATION_DURATION);
        mFloatValueAnimator.start();
    }

    @Override
    public void stop() {
        mFloatValueAnimator.removeAllUpdateListeners();
        mFloatValueAnimator.removeAllListeners();
        mFloatValueAnimator.setRepeatCount(0);
        mFloatValueAnimator.setDuration(0);
        mFloatValueAnimator.end();
    }

    @Override
    public boolean isRunning() {
        return mFloatValueAnimator.isRunning();
    }
}
