package com.example.mydemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * time   : 2018/05/28
 * desc   :  Scroller 方法的相关介绍
 * version: 1.0.0
 */

public class MyScroller extends View {

    private Scroller mScroller;
    private int lastX;
    private int lastY;

    public MyScroller(Context context) {
        super(context);
        initView(context);
    }

    public MyScroller(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyScroller(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        //初始化 Scroller
        mScroller = new Scroller(context);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
                //抬起  要平滑的回到  起始点

                // 获取 子 View的移动的距离  -  getScrollX()  getScrollY();

                View viewGroup = ((View) getParent());
                mScroller.startScroll(
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY());
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                //当移动的时候
                int offetX = x - lastX;
                int offetY = y - lastY;
//                layout(getLeft() + offetX, getTop() + offetY, getRight() + offetX, getBottom() + offetY);

                //这两个等价于 上面的 layout();
                offsetLeftAndRight(offetX);
                offsetTopAndBottom(offetY);

                break;


            default:
                break;
        }

        return true;//拦截事件

    }


    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            // mScroller.getCurrX()   mScroller.getCurrY()   来获取当前滑动的坐标
            ((View) getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //通过绘制类不断的调用 computeScroll
            invalidate();
        }
    }
}
