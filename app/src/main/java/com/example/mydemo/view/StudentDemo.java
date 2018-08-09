package com.example.mydemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * author : 宋佳
 * time   : 2018/08/08
 * desc   :
 * version: 1.0.0
 */

public class StudentDemo extends View {
    public StudentDemo(Context context) {
        super(context, null);
    }

    public StudentDemo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public StudentDemo(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);


        //EXACTLY 没有具体的上限  但是不能超过父View的大小  具体的值 大小
        //AT_MOST 父View给子View具体的大小  WRAP_content

        if (modeWidth == MeasureSpec.EXACTLY) {
            Log.i("XXXXX", "EXACTLY");
        } else if (modeWidth == MeasureSpec.AT_MOST) {
            Log.i("XXXXX", "AT_MOST");
        }


        if (modeHeight == MeasureSpec.EXACTLY) {
            Log.i("YYYYY", "EXACTLY");
        } else if (modeHeight == MeasureSpec.AT_MOST) {
            Log.i("YYYYY", "AT_MOST");
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);

    }
}
