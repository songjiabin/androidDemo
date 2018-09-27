package com.example.mydemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * author : 宋佳
 * time   : 2018/09/12
 * desc   :
 * version: 1.0.0
 */

public class SkipRecycleView extends RecyclerView {

    // 判断是否移动的距离
    private final int mScaleTouchSlop;


    private int mLastX;
    private int mLastY;
    private int mDownX;
    private int mDownY;

    //最后点击一个view
    private int mLastTouchPosition;

    public SkipRecycleView(Context context) {
        this(context, null);
    }

    public SkipRecycleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkipRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mScaleTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        boolean intercepted = false;


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                mDownX = (int) event.getX();
                mDownY = (int) event.getY();
                mLastX = (int) event.getX();
                mLastY = (int) event.getY();
                intercepted = false;
                //调用recylerview 的onInterceptTouchEvent方法初始化mActivePointerId
                super.onInterceptTouchEvent(event);
                break;
            case MotionEvent.ACTION_MOVE:
                //拦截 横向的事件
                //横坐标位移增量
                int currentX = (int) event.getX();
                int currentY = (int) event.getY();

                //横坐标位移增量
                int deltaX = currentX - mLastX;
                //纵坐标位移增量
                int deltaY = currentY - currentY;


                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercepted = false;
                } else {
                    intercepted = true;
                }





                break;
        }

        return intercepted;

    }
}
