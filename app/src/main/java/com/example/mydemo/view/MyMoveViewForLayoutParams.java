package com.example.mydemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * time   : 2018/05/25
 * desc   : 跟随手势 滑动的 View
 * 通过 LayoutParams 来进行改变的
 * version: 1.0.0
 */

public class MyMoveViewForLayoutParams extends View {
    private int lastX;
    private int lastY;
    private LinearLayout.LayoutParams layoutParams;

    public MyMoveViewForLayoutParams(Context context) {
        super(context);
        initView(context);
    }

    public MyMoveViewForLayoutParams(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyMoveViewForLayoutParams(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //注意 看清 父布局是什么类型的     当然可以使用通用的   ViewGroup.MarginLayoutParams
//        ViewGroup.MarginLayoutParams  p= (ViewGroup.MarginLayoutParams) getLayoutParams();

        layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
                //抬起
                break;
            case MotionEvent.ACTION_MOVE:
                //当移动的时候  获取的偏移量
                int offetX = x - lastX;
                int offetY = y - lastY;


//                layoutParams.leftMargin = getLeft() + offetX;
//                layoutParams.rightMargin = getRight() + offetX;
//                layoutParams.topMargin = getTop() + offetY;
//                layoutParams.bottomMargin = getBottom() + offetY;
//
//                this.setLayoutParams(layoutParams);


                //利用SrcrollBy（） 进行偏移量
                ((View) getParent()).scrollBy(-offetX, -offetY);


                break;
            default:
                break;
        }

        return true;//拦截事件
    }
}
