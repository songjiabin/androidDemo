package com.example.mydemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.Scroller;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/07/17
 * desc   : 跑马灯
 * version: 1.0.0
 */

public class MarqueeView extends android.support.v7.widget.AppCompatTextView {


    /**
     * 默认滚动时间
     */
    private static final int ROLLING_INTERVAL_DEFAULT = 10000;
    /**
     * 滚动模式-一直滚动  默认的
     */
    public static final int SCROLL_FOREVER = 100;

    /**
     * 滚动模式-只滚动一次
     */
    public static final int SCROLL_ONCE = 101;

    /**
     * 第一次滚动默认延迟
     */
    private static final int FIRST_SCROLL_DELAY_DEFAULT = 1000;

    /**
     * 初次滚动时间间隔
     */
    private int mFirstScrollDelay;


    private Scroller scroller = null;
    /**
     * 滚动的初始 X 位置
     */
    private int mXPaused = 0;
    /**
     * 是否暂停
     */
    private boolean mPaused = true;

    /**
     * 是否第一次
     */
    private boolean mFirst = true;

    private int mRollingInterval;
    private int mScrollMode;

    public MarqueeView(Context context) {
        super(context);
    }

    public MarqueeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MarqueeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView(Context context, AttributeSet attrs) {


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.marqueeTextView);
        //得到 滚动的时间间隔
        mRollingInterval = typedArray.getInt(R.styleable.marqueeTextView_scroll_interval, ROLLING_INTERVAL_DEFAULT);

        //得到滚动的模式
        mScrollMode = typedArray.getInt(R.styleable.marqueeTextView_scroll_mode, SCROLL_FOREVER);

        //第一次的延迟时间
        typedArray.getInt(R.styleable.marqueeTextView_scroll_first_delay, FIRST_SCROLL_DELAY_DEFAULT);


        typedArray.recycle();
        setSingleLine();
        setEllipsize(null);

    }

    //需要计算滚动的距离
    private int calculateScrollingLen() {
        Paint paint = getPaint();
        String textString = getText().toString();
        Rect rect = new Rect();
        paint.getTextBounds(textString, 0, textString.length(), rect);
        return rect.width();
    }


    //开始滚动
    public void startSrcoller() {


        if (!mPaused) {
            return;
        }

        if (scroller == null) {
            setHorizontallyScrolling(true);
            // LinearInterpolator 差值器
            scroller = new Scroller(this.getContext(), new LinearInterpolator());
            setScroller(scroller);
        }
        //得到滚动的距离
        final int scrollingLen = calculateScrollingLen();
        final int distance = scrollingLen - mXPaused;
        final int duration = (Double.valueOf(mRollingInterval * distance * 1.00000
                / scrollingLen)).intValue();
        if (mFirst) {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    scroller.startScroll(mXPaused, 0, distance, 0, duration);
                    invalidate();
                    mPaused = false;
                }
            }, mFirstScrollDelay);
        } else {
            scroller.startScroll(mXPaused, 0, distance, 0, duration);
            invalidate();
            mPaused = false;
        }
    }


    /**
     * 重新开始滚动
     */
    public void reStartScroll() {
        mXPaused = 0;
        mPaused = true;
        mFirst = true;
        startSrcoller();
    }


    /**
     * 暂停滚动
     */
    public void pauseScroll() {
        if (null == scroller)
            return;

        if (mPaused)
            return;

        mPaused = true;

        mXPaused = scroller.getCurrX();

        scroller.abortAnimation();
    }

    /**
     * 停止滚动，并回到初始位置
     */
    public void stopScroll() {
        if (null == scroller) {
            return;
        }
        mPaused = true;
        scroller.startScroll(0, 0, 0, 0, 0);
    }


    //完成滚动的监听
    @Override
    public void computeScroll() {
        super.computeScroll();
        if (null == scroller) {
            return;
        }


        if (scroller.computeScrollOffset()) {
            //动画完成

        }

        //判断Scroller是否执行完毕
        if (scroller.isFinished() && (!mPaused)) {
            if (mScrollMode == SCROLL_ONCE) {
                stopScroll();
                return;
            }
            mPaused = true;
            mXPaused = -1 * getWidth();
            mFirst = false;
            this.startSrcoller();
        }
    }
}
