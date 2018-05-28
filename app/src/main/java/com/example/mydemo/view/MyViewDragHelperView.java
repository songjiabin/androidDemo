package com.example.mydemo.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * author : 宋佳
 * time   : 2018/05/28
 * desc   :
 * version: 1.0.0
 */

public class MyViewDragHelperView extends ViewGroup {


    private ViewDragHelper mViewDragHelper;
    private View mMenuView;
    private View mMainView;

    public MyViewDragHelperView(Context context) {
        super(context);
    }

    public MyViewDragHelperView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewDragHelperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView() {
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }

    //加载完布局后使用
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


    //由上到下的拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //将事件传递给 ViewDragHelper 进行处理
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件传递给ViewDragHelper。此操作挺重要的。
        mViewDragHelper.processTouchEvent(event);
        return true;
    }


    //scroller的回调方法  因为ViewDragHelper的内容也是通过Scroller来实现平滑移动的。
    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    /**
     * ViewDragHelper的 回调方法
     */
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        /**
         * 通过这个方法。我们可以指定在创建 ViewDragHelper时，参数parentView中的哪一个View可以被移动
         * 比如在自定义的ViewGroup中。里面定义了两个子View
         * 一： MenuView
         * 二： MainView
         * 当指定如下代码时， 只有MainView是可以被拖动的
         */
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mMainView == child;
        }

        /**
         * 水平的滑动
         * 想要滑动必须重写  默认的返回值 是 0 即：不发生滑动
         *
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return super.clampViewPositionHorizontal(child, left, dx);
        }

        /**
         * 竖直方向的滑动
         * 想要滑动必须重写  默认的返回值 是 0 即：不发生滑动
         * @param top   代表 在垂直方向上 child 移动的距离
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return super.clampViewPositionVertical(child, top, dy);
        }
    };

}
