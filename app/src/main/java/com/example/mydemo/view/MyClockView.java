package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * time   : 2018/05/29
 * desc   :自定义时钟
 * version: 1.0.0
 */

public class MyClockView extends View {
    private Context context;
    private Paint paintCirle;
    private int mWidth;
    private int mHeight;
    private Paint mPaintDegree;

    public MyClockView(Context context) {
        super(context);
        initView(context);
    }

    public MyClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        paintCirle = new Paint();
        paintCirle.setStyle(Paint.Style.STROKE);
        paintCirle.setAntiAlias(true);
        paintCirle.setStrokeWidth(5);


        //刻度大
        mPaintDegree = new Paint();
        mPaintDegree.setStyle(Paint.Style.STROKE);
        mPaintDegree.setAntiAlias(true);
        mPaintDegree.setStrokeWidth(3);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画一个画  圆点在屏幕中心   半径为屏幕宽度的一半

        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2, paintCirle);
        canvas.save();

        //画 指针

        for (int i = 0; i < 24; i++) {
            //区分整点和非整点
            if (i == 0 || i == 6 || i == 12 || i == 18) {
                mPaintDegree.setStrokeWidth(5);
                mPaintDegree.setTextSize(30);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 60,
                        mPaintDegree);
                String degree = String.valueOf(i);
                canvas.drawText(degree,
                        mWidth / 2 - mPaintDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 90,
                        mPaintDegree);
            } else {
                mPaintDegree.setStrokeWidth(3);
                mPaintDegree.setTextSize(15);
                canvas.drawLine(mWidth / 2, mHeight / 2 - mWidth / 2,
                        mWidth / 2, mHeight / 2 - mWidth / 2 + 30,
                        mPaintDegree);
                String degree = String.valueOf(i);

                // mPaintDegree.measureText(degree) 测量Text的宽度
                canvas.drawText(degree,
                        mWidth / 2 - mPaintDegree.measureText(degree) / 2,
                        mHeight / 2 - mWidth / 2 + 60,
                        mPaintDegree);
            }

            //以中间为圆心 每次转15°
            canvas.rotate(15, mWidth / 2, mHeight / 2);
        }


        // 画圆心
        Paint paintPointer = new Paint();
        paintPointer.setStrokeWidth(30);
        canvas.drawPoint(mWidth / 2, mHeight / 2, paintPointer);
        // 画指针
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        canvas.save();
        //将画布移动到中心位置 即：将坐标点定在了 圆的中心位置
        canvas.translate(mWidth / 2, mHeight / 2);
        canvas.drawLine(0, 0, 100, 100, paintHour);
        canvas.drawLine(0, 0, 100, 200, paintMinute);
        canvas.restore();


    }
}
