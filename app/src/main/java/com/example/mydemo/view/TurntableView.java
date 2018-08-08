package com.example.mydemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/08/08
 * desc   : 自定义转盘
 *  原文链接： https://mp.weixin.qq.com/s?__biz=MzU4MTU3NTM3MQ==&mid=2247484107&idx=1&sn=3c343e559d6c97aa7bc05df5f9fd5554&chksm=fd44356fca33bc790f4d27cc6203b34c1a390c8716799b987231434a3b607781aef2b8ae2642&mpshare=1&scene=1&srcid=0808lDSTrshloGACcTtIjYb5#rd
 * version: 1.0.0
 */

public class TurntableView extends View {


    /**
     * 扇形分区的颜色
     */
    private int[] colors = new int[]{android.R.color.holo_red_dark, android.R.color.darker_gray, android.R.color.holo_blue_dark, android.R.color.holo_red_dark, android.R.color.darker_gray, android.R.color.holo_blue_dark};


    private Paint mArcPaint;


    /**
     * 图片对应的bitmap
     */
    private Bitmap[] mImgBitmap;

    /**
     * 盘块的数量
     */
    private int mItems = 6;

    /**
     * 扇形分区图片
     */
    private int[] imgs = new int[]{R.drawable.like_1, R.drawable.like_2, R.drawable.like_3, R.drawable.like_4
            , R.drawable.like_5, R.drawable.like_6};


    /**
     * 文字的大小
     */
    private float mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics());
    private Paint mTextPaint;

    /**
     * 分区的文字描述
     */
    private String[] prizeName = new String[]{"微信红包", "王者皮肤", "谢谢参与", "1QB", "5QB", "10QB"};
    /**
     * 圆盘背景图片
     */
    private Bitmap mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg2);


    /**
     * 圆盘Padding值
     */
    private int mPadding;


    /**
     * 圆盘的直径
     */
    private int mRadius;


    /**
     * 圆盘的大小范围
     */
    private RectF mRange = new RectF();
    /**
     * 转盘的中心位置
     */
    private int mCenter;
    /**
     * 是否点击了结束按钮
     */
    private boolean start = false;

    /**
     * 绘制的起始角度
     */
    private double mStartAngle;

    /**
     * 圆盘滚动的速度
     */
    private double mSpeed = 50;


    private Handler mHandler = new Handler();


    public TurntableView(Context context) {
        super(context);
        initView();
    }

    public TurntableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        //初始化盘块画笔
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true); //设置抗锯齿
        mArcPaint.setDither(true); //默认设置不抖动效果


        //初文字画笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(mTextSize);


        //初始化盘块中的图片
        mImgBitmap = new Bitmap[mItems];
        for (int i = 0; i < mItems; i++) {
            mImgBitmap[i] = BitmapFactory.decodeResource(getContext().getResources(), imgs[i]);
        }


    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取宽高的最小值
        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
        //以paddingleft为准
        mPadding = getPaddingLeft();
        //获取直径
        mRadius = width - mPadding * 2;

        setMeasuredDimension(width, width);

        //初始化圆盘的范围
        mRange = new RectF(mPadding, mPadding, mRadius + mPadding, mRadius + mPadding);

        //中心位置
        mCenter = getMeasuredWidth() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景
        //1.绘制背景
        canvas.drawBitmap(mBgBitmap, null, new RectF(mPadding / 2, mPadding / 2
                , getMeasuredWidth() - mPadding / 2, getMeasuredHeight() - mPadding / 2), null);


        //2.绘制盘块
        int tempAngle = (int) mStartAngle;

        //每个块的角度
        float sweepAngle = 360 / mItems;


        for (int i = 0; i < mItems; i++) {
            mArcPaint.setColor(ContextCompat.getColor(getContext(), colors[i]));
            canvas.drawArc(mRange, tempAngle, sweepAngle, true, mArcPaint);


            //2.绘制盘块的文字
            Path path = new Path();
            path.addArc(mRange, tempAngle, sweepAngle);

            //通过水平偏移量使得文字居中  水平偏移量=弧度/2 - 文字宽度/2
            float textWidth = mTextPaint.measureText(prizeName[i]);
            // 周长 = 2πr      mRadius * Math.PI = 周长
            float hOffset = (float) (mRadius * Math.PI / mItems / 2 - textWidth / 2);
            //垂直偏移量 = 半径/6
            float vOffset = mRadius / 2 / 6;
            canvas.drawTextOnPath(prizeName[i], path, hOffset, vOffset, mTextPaint);


            // 3.绘制盘块上面的IMG
            //约束下图片的宽度
            int imgWidth = mRadius / 8;
            //获取弧度
            float angle = (float) Math.toRadians(tempAngle + sweepAngle / 2);
            //将图片移动到圆弧中心位置
            float x = (float) (mCenter + mRadius / 2 / 2 * Math.cos(angle));
            float y = (float) (mCenter + mRadius / 2 / 2 * Math.sin(angle));
            //确认绘制的矩形
            RectF rectF = new RectF(x - imgWidth / 2, y - imgWidth / 2, x + imgWidth / 2, y + imgWidth / 2);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs[i]);
            canvas.drawBitmap(bitmap, null, rectF, null);


            tempAngle += sweepAngle;

        }
        if (start) {
            mStartAngle += mSpeed;
            //16ms之后刷新界面
            mHandler.postDelayed(new MyRunnable(), 16);
            mSpeed -= 1;
            if (mSpeed < 10) {
                mSpeed -= 0.5;
            }
            if (mSpeed < 3) {
                mSpeed -= 0.1;
            }
            if (mSpeed < 0) {
                mSpeed = 0;
                start = false;
            }
        }

    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            invalidate();
        }
    }

    public void start() {
        start = true;
        mSpeed = 40;
        invalidate();
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        start();
    }
}
