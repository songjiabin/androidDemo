package com.example.mydemo.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * author : 宋佳
 * time   : 2018/05/28
 * desc   :
 * version: 1.0.0
 */

public class MyViewDragHelperView extends FrameLayout {


    private ViewDragHelper mViewDragHelper;
    private View mMenuView;
    private View mMainView;
    private int mWidth;

    public MyViewDragHelperView(Context context) {
        super(context);
        initView();
    }

    public MyViewDragHelperView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyViewDragHelperView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuView.getMeasuredWidth();
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
         * 一般情况下需要返回left即可  需要更加精确的计算padding等属性的时候，需要对left进行一些处理 并返回合适大小的值
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        /**
         * 竖直方向的滑动
         * 想要滑动必须重写  默认的返回值 是 0 即：不发生滑动
         * 一般情况下需要返回top即可  需要更加精确的计算padding等属性的时候，需要对left进行一些处理 并返回合适大小的值
         * @param top   代表 在垂直方向上 child 移动的距离
         * @param dy    代表 比较前一次的增量
         *
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        //拖动结束后的回调方法
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //手指抬起后缓缓的移动的指定的位置

            if (mMainView.getLeft() < 500) {
                //关闭菜单
                //相当于Scroller的startScroll
                mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                //刷新界面
                ViewCompat.postInvalidateOnAnimation(MyViewDragHelperView.this);
            } else {
                //打开菜单
                mViewDragHelper.smoothSlideViewTo(mMainView, 300, 0);
                ViewCompat.postInvalidateOnAnimation(MyViewDragHelperView.this);

            }


        }
    };

}
