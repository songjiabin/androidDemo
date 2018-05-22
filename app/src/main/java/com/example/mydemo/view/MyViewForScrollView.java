package com.example.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.example.mydemo.utils.ScreenUtils;

/**
 * 自定义ViewGroup
 * 让里面的View具有黏性的效果
 */

public class MyViewForScrollView extends ViewGroup {

    private int mScreenHeight;
    private int mLastY;
    private int mStart;
    private Scroller mScroller;


    public MyViewForScrollView(Context context) {
        super(context);
        initView(context);
    }

    public MyViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyViewForScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //得到屏幕的高度
        mScreenHeight = ScreenUtils.getScreenHeight(context);
        //我把scroller理解为滚动数据携带器，
        // 他只是一个记录滚动数据的工具，并不显示view的滚动效果，其实这点我觉得和安卓的属性动画挺像，他只提供数据，具体要怎么做，就看你的了。
        mScroller = new Scroller(context);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //在放置View的时候 要先确定整个ViewGroup的高度
        //本个列中 让每个View都占一屏，所有整个ViewGroup的高度就是
        int childCount = getChildCount();
        // 设置ViewGroup的高度
        ViewGroup.LayoutParams mlp = (ViewGroup.LayoutParams) getLayoutParams();
        mlp.height = mScreenHeight * childCount;
        //遍历每个View放置的位置 具体通过 每个View的 layout()方法。
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            //调用每个View的layout()方法
            view.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);

        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //需要设置每个里面的View 。所有要遍历里面的View进行测量
        int size = getChildCount();
        for (int i = 0; i < size; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取相对于控件的坐标
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下的时候
                mLastY = y;
                //获取偏移量 相对于起始位置的偏移量
                mStart = getScrollY();
//                Log.i("SJB", mStart + "");
                break;
            case MotionEvent.ACTION_MOVE:
                //移动的时候
                if (!mScroller.isFinished()) {
//                    Scroller滚动到最终x与y位置时中止动画。
                    mScroller.abortAnimation();
                }
                // Y的偏移量 =   当时按下的 Y坐标  - 当前的坐标
                int dy = mLastY - y;


                int getScrollY = getScrollY();
                Log.i("SJB", "--------getScrollY------" + getScrollY );


                int height = getHeight() - mScreenHeight;

                if (getScrollY() < 0) {
                    dy = 0;
                }

                //如果便宜量 >
                if (getScrollY() > getHeight() - mScreenHeight) {
                    dy = 0;
                }
                scrollBy(0, dy);
                mLastY = y;

                break;
            case MotionEvent.ACTION_UP:
                int dScrollY = checkAlignment();
                if (dScrollY > 0) {
                    if (dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -dScrollY);
                    } else {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, mScreenHeight - dScrollY);
                    }
                } else {
                    if (-dScrollY < mScreenHeight / 3) {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -dScrollY);
                    } else {
                        mScroller.startScroll(
                                0, getScrollY(),
                                0, -mScreenHeight - dScrollY);
                    }
                }
                break;
        }
        //异步刷新界面
        postInvalidate();
        return true;

    }

    private int checkAlignment() {
        Log.i("SJB", "--------getScrollY------" + getScrollY() );
        int mEnd = getScrollY();
        boolean isUp = ((mEnd - mStart) > 0) ? true : false;
        int lastPrev = mEnd % mScreenHeight;
        int lastNext = mScreenHeight - lastPrev;
        if (isUp) {
            //向上的
            return lastPrev;
        } else {
            return -lastNext;
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
