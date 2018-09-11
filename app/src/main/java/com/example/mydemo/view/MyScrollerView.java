package com.example.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * author : 宋佳
 * time   : 2018/08/30
 * desc   :
 * version: 1.0.0
 */

public class MyScrollerView extends RelativeLayout {


    private boolean s1 = true;
    Scroller mScroller = null;

    public MyScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (mScroller.computeScrollOffset()) {
                Log.d("》》》", "11111111");
            }
            scrollTo(mScroller.getCurrX(), 0);
            postInvalidate();
        }
    }

    //需要手动执行这个方法
    public void beginScroll() {
        if (!s1) {
            mScroller.startScroll(0, 0, 0, 0, 10000);
            s1 = true;
        } else {
            mScroller.startScroll(0, 0, 500, 0, 10000);
            s1 = false;
        }
        invalidate();
    }


}
