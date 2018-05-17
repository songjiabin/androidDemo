package com.example.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydemo.utils.ScreenUtils;

/**
 * 自定义ViewGroup
 * 让里面的View具有黏性的效果
 */

public class MyViewForScrollView extends ViewGroup {

    private int mScreenHeight;
    private int mLastY;
    private int mStart;

    public MyViewForScrollView(Context context) {
        super(context);
        initView(context);
    }

    public MyViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewForScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //得到屏幕的高度
        mScreenHeight = ScreenUtils.getScreenHeight(context);

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
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下的时候
                mLastY = y;
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                //移动的时候
                break;
        }


        return super.onTouchEvent(event);

    }
}
