package com.example.mydemo.event;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * author : 宋佳
 * time   : 2018/07/12
 * desc   :
 * version: 1.0.0
 */

public class MyViewPager2 extends ViewPager {


    public MyViewPager2(Context context) {
        super(context);
    }

    public MyViewPager2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 拦截事件  改成不拦截
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        if (action == MotionEvent.ACTION_DOWN) {
            super.onInterceptTouchEvent(ev);
            return false;
        }

        return true;
    }
}
