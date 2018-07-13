package com.example.mydemo.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * author : 宋佳
 * time   : 2018/07/13
 * desc   :
 * version: 1.0.0
 */

public class FixListView extends ListView {

    private int mLastX;
    private int mLastY;

    public FixListView(Context context) {
        super(context);
    }

    public FixListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        final int action = ev.getAction() & MotionEvent.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                //水平移动的增量
                int deltaX = x - mLastX;
                //竖直移动的增量
                int deltaY = y - mLastY;
                //当水平增量大于竖直增量时，表示水平滑动，此时需要父View去处理事件
                if (Math.abs(deltaX) > Math.abs(deltaY)){
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);

    }
}
