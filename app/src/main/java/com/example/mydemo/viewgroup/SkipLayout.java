package com.example.mydemo.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/09/11
 * desc   : qq侧滑   item 布局  的自定义
 * version: 1.0.0
 */

public class SkipLayout extends LinearLayout {

    // 这个
    private int itemId;
    private int mDownX, mDownY, mLastX; // 按下X  按下Y  最后一次按下 X  的位置


    public int currentScrollState = 0;//当前滑动的状态 左滑？又滑？

    public static final int STATE_OPEN = 1;//打开开关
    public static final int STATE_MOVING_LEFT = 2;//左滑将要打开状态
    public static final int STATE_MOVING_RIGHT = 3;//左滑将要打开状态
    private View itemView;

    // 引用次滑动布局的宽度
    private int menuWidth;


    public SkipLayout(Context context) {
        this(context,null);

    }


    public SkipLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SkipLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs,defStyleAttr);
        setOrientation(HORIZONTAL);//水平排布
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySkipStyle);


        itemId = typedArray.getResourceId(R.styleable.MySkipStyle_item_id, 0);

        typedArray.recycle();

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (itemId != 0) {
            // 得到 这个item 或者说是 得到 需要滑动里面内容布局 View
            itemView = (View) findViewById(itemId);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        menuWidth = itemView.getMeasuredWidth();
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //摁下的时候
                mDownX = (int) event.getX();
                mDownY = (int) event.getY();
                mLastX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                // 移动的时候
                int dx = (int) (mDownX - event.getX());
                int dy = (int) (mDownY - event.getY());
                //如果Y轴偏移量大于X轴偏移量 不再滑动
                //因为这里接受左右滑动的事件  所以屏幕掉 上下滑动
                if (Math.abs(dy) > Math.abs(dx))
                    return false;

                //左右滑动的距离
                int deltalX = (int) (mLastX - event.getX());
                if (deltalX > 0) {
                    //向左滑动
                    currentScrollState = STATE_MOVING_LEFT;


                    Log.i("宋佳宾","deltalX--->   "+deltalX);

                    Log.i("宋佳宾","menuWidth--->   "+menuWidth);

                    Log.i("宋佳宾","getScrollX--->   "+getScrollX());


                    if (deltalX >= menuWidth || getScrollX() + deltalX >= menuWidth) {
                        //右边缘检测
                       /* scrollTo(menuWidth, 0);
                        currentState = STATE_OPEN;*/
                        break;
                    }

                } else {
                    //向右滑动
                    currentScrollState = STATE_MOVING_RIGHT;
                }


                break;
            default:
                break;
        }


        return super.onTouchEvent(event);

    }
}
