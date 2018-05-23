package com.example.mydemo.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * onMesure() 测量简单模式
 */
public class MyView extends View {


    private Context context;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measeureWidth(widthMeasureSpec), measeureHight(heightMeasureSpec));
    }

    /**
     * 测量宽度
     *
     * @param widthMeasureSpec
     */
    private int measeureWidth(int widthMeasureSpec) {
        int size = 0;
        //得到測量模式
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        //得到測量的大小
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            //测量模式是 固定值 或者是 MATCH_PARENT
            //因为测量模式是固定的模式 所以直接用测量的大小就好了。
            size = specSize;
        } else {
            //是其他两种 表示不是固定的大小 需要你进行指定它的大小
            size = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                //表示是wrap_parent 需要你进行比较 选取最小的那个
                size = Math.min(size, specSize);
            }
        }
        return size;
    }

    /**
     * 测量高度
     *
     * @param hightMeasureSpec
     */
    private int measeureHight(int hightMeasureSpec) {
        int size = 0;
        //得到測量模式
        int specMode = MeasureSpec.getMode(hightMeasureSpec);
        //得到測量的大小
        int specSize = MeasureSpec.getSize(hightMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            //测量模式是 固定值 或者是 MATCH_PARENT
            //因为测量模式是固定的模式 所以直接用测量的大小就好了。
            size = specSize;
        } else {
            //是其他两种 表示不是固定的大小 需要你进行指定它的大小
            size = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                //表示是wrap_parent 需要你进行比较 选取最小的那个
                size = Math.min(size, specSize);
            }
        }
        px2dp(context, Float.valueOf(size));

        return size;

    }

    /**
     * px转换成dp
     */
    private void px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        Log.i("数据是：", (int) (pxValue / scale + 0.5f) + "");
    }




}
