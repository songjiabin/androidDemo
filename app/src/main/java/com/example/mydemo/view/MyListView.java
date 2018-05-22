package com.example.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.EventLogTags;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

public class MyListView extends ListView implements View.OnTouchListener {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * View的监听事件
     * 通过 ACTION_DOWN、 ACTION_UP 、 ACTION_MOVE 这三个发生的坐标就能确定用户滑动的方向
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_UP) {
            //抬起的时候
        } else if (eventAction == MotionEvent.ACTION_DOWN) {
            //按下的时候
        } else if (eventAction == MotionEvent.ACTION_MOVE) {
            //移动的时候
        }
        return false;//true 拦截  false不拦截
    }
}
