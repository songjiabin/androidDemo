package com.example.mydemo.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * author :  宋佳
 * time   :
 * desc   :  自定义ViewGroup 需求：我们定义一个ViewGroup，内部可以传入0到4个childView。第一、第二行分别两个 。
 * version:  1.0.0
 */

public class OtherViewGroup extends ViewGroup {


    public OtherViewGroup(Context context) {
        super(context);
    }


    public OtherViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OtherViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //应该现在是明确的因为现在有四个  View

        //测量每个子View
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);


        //根据子View 来确定ViewGroup的大小

        //确定宽度


        int childCount = getChildCount();
        if (childCount == 0) {
            //说明没有子View 。那么当前ViewGroup没有存在的意思。不用占用
            setMeasuredDimension(0, 0);
        } else {
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                // 宽高给出的都是允许范围内的最大值
                int maxWithOfFirstAndTwo = getMaxWithOfFirstAndTwo();


                int maxWitdth = Math.min(sizeWidth, maxWithOfFirstAndTwo);


                int maxHeightOfFirstAndTwo = getMaxHeightOfFristAndTwo();

                int maxHeight = Math.min(sizeHeight, maxHeightOfFirstAndTwo);


                setMeasuredDimension(maxWitdth, maxHeight);
            } else if (widthMode == MeasureSpec.AT_MOST) {
                //宽度是  允许最大的值  高度是固定的值
                int maxWithOfFirstAndTwo = getMaxWithOfFirstAndTwo();


                int maxWitdth = Math.min(sizeWidth, maxWithOfFirstAndTwo);
                setMeasuredDimension(maxWitdth, sizeHeight);

            } else if (heightMode == MeasureSpec.AT_MOST) {
                int maxHeightOfFirstAndTwo = getMaxHeightOfFristAndTwo();

                int maxHeight = Math.min(sizeHeight, maxHeightOfFirstAndTwo);
                setMeasuredDimension(sizeWidth, maxHeight);
            }


        }


    }

    /**
     * 得到 第一行和第二行中最大的宽度
     *
     * @return
     */
    private int getMaxWithOfFirstAndTwo() {

        View viewLeftTop = getChildAt(0); //左上角
        View viewRightTop = getChildAt(1);//右上角
        View viewLeftBottom = getChildAt(2);//左下角
        View viewRightBottom = getChildAt(3);//右下角

        int firstLineWidth = viewLeftTop.getMeasuredWidth() + viewRightTop.getMeasuredWidth();
        int twoLineWidth = viewLeftBottom.getMeasuredWidth() + viewRightBottom.getMeasuredWidth();
        return Math.max(firstLineWidth, twoLineWidth);
    }

    /**
     * 得到 第一列 和第二列中最高的高度
     *
     * @return
     */
    private int getMaxHeightOfFristAndTwo() {


        View viewLeftTop = getChildAt(0); //左上角
        View viewRightTop = getChildAt(1);//右上角
        View viewLeftBottom = getChildAt(2);//左下角
        View viewRightBottom = getChildAt(3);//右下角

        //设置高度
        int firstColumnHeight = viewLeftTop.getMeasuredHeight() + viewLeftBottom.getMeasuredHeight();
        int twoColumnHeight = viewRightTop.getMeasuredHeight() + viewRightBottom.getMeasuredHeight();

        return Math.max(firstColumnHeight, twoColumnHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        //记录当前的最大值
        int currentWidth = 0;
        int currentHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (i == 0) {
                //第一个 View
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                currentWidth = view.getMeasuredWidth();
                currentHeight = view.getMeasuredHeight();
            } else if (i == 1) {
                //第二个View
                view.layout(currentWidth, 0, view.getMeasuredWidth() + currentWidth, view.getMeasuredHeight());
                currentWidth = 0;
                currentHeight = view.getMeasuredHeight() > currentHeight ? view.getMeasuredHeight() : currentHeight;
            } else if (i == 2) {
                view.layout(0, currentHeight, view.getMeasuredWidth(), currentHeight + view.getMeasuredHeight());
                currentWidth = view.getMeasuredWidth();
            } else if (i == 3) {
                view.layout(currentWidth, currentHeight, view.getMeasuredWidth() + currentWidth, currentHeight + view.getMeasuredHeight());
            }
        }
    }
}
