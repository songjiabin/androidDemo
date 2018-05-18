package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 重写View实现全新的控件
 */

public class MyProgressBar extends View {

    private float mRadius;
    private int mCircleXY;
    private Paint mPaint;
    private int widthSize;
    private int heightSize;
    private RectF mArcRectF;
    private Paint mArcPaint;
    private float mSweepValue = 66;
    private float mSweepAngle;
    private String mShowText;
    private float mShowTextSize;
    private Paint mTextPaint;

    public MyProgressBar(Context context) {
        super(context);
    }

    public MyProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(context.getResources().getColor(android.R.color.holo_red_dark));


    }


    public MyProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthSize = MeasureSpec.getSize(widthMeasureSpec);
        heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
        initView();

    }

    private void initView() {
        int length = 0;
        if (heightSize >= widthSize) {
            length = widthSize;
        } else length = heightSize;
        //圆心的 x y 坐标
        mCircleXY = length / 2;
        //圆的半径
        mRadius = (float) (length * 0.5 / 2);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(android.R.color.holo_red_dark));

        //设置椭圆的属性

        mArcRectF = new RectF(
                (float) (length * 0.1),
                (float) (length * 0.1),
                (float) (length * 0.9),
                (float) (length * 0.9));
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(getResources().getColor(
                android.R.color.holo_blue_bright));
        //设置边框的粗细
        mArcPaint.setStrokeWidth((float) (length * 0.1));
        //空心
        mArcPaint.setStyle(Paint.Style.STROKE);
        //占 66/360 分之一
        mSweepAngle = (mSweepValue / 100f) * 360f;

        //绘制圆内的文字

        mShowText = setShowText();
        mShowTextSize = setShowTextSize();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);


    }

    private String setShowText() {
        this.invalidate();
        return "Android Skill";
    }

    private float setShowTextSize() {
        this.invalidate();
        return 50;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制圆
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mPaint);
        // 绘制弧线 从-90位置开始顺时针 画 mSweepAngle 个角度
        canvas.drawArc(mArcRectF, -90, mSweepAngle, false, mArcPaint);
        // 绘制文字
        canvas.drawText(mShowText, 0, mShowText.length(),
                mCircleXY, mCircleXY + (mShowTextSize / 4), mTextPaint);
    }

    public void setSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            mSweepValue = sweepValue;
        } else {
            mSweepValue = 25;
        }
        mSweepAngle = (mSweepValue / 100f) * 360f;
        this.invalidate();
    }

}
