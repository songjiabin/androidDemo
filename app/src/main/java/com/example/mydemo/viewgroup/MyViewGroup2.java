package com.example.mydemo.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * author : 宋佳
 * time   : 2018/06/08
 * desc   : 自定义ViewGroup 需求：我们定义一个ViewGroup，内部可以传入0到4个childView，分别依次显示在左上角，右上角，左下角，右下角。
 * version: 1.0.0
 */

public class MyViewGroup2 extends ViewGroup {


    public MyViewGroup2(Context context) {
        super(context);
    }

    public MyViewGroup2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 在onMeasure中计算childView的测量值以及模式，以及设置自己的宽和高：
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //获取 此个ViewGroup 上层建议他的测量模式和大小

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        /**
         * 测量每个子View
         */
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        MarginLayoutParams cParams = null;
        // 子View测量得到的宽度
        int cMeasureWidth;
        int cMeasureHeight;

        //上面两个View需要的总高度
        int topWidth = 0;
        //下面两个View需要的总高度
        int bottomWidth = 0;


        //左边两个需要的高度
        int leftHeight = 0;
        //右边两个需要的高度
        int rightHeight = 0;


        for (int i = 0; i < getChildCount(); i++) {

            View childView = getChildAt(i);
            cParams = (MarginLayoutParams) childView.getLayoutParams();
            cMeasureWidth = childView.getMeasuredWidth();
            cMeasureHeight = childView.getMeasuredHeight();

            //计算ViewGroup最大的宽高
            if (i == 0 || i == 1) {
                //得到第一和第二个View
                topWidth += cMeasureWidth + cParams.leftMargin + cParams.rightMargin;
            }

            if (i == 2 || i == 3) {
                //得到第三个和第四个View
                bottomWidth += cMeasureWidth + cParams.leftMargin + cParams.rightMargin;
            }


            if (i == 0 || i == 2) {
                //得到第一个和第三个View
                leftHeight += cMeasureHeight + cParams.topMargin + cParams.bottomMargin;
            }

            if (i == 1 || i == 3) {
                //得到第一个和第三个View
                rightHeight += cMeasureHeight + cParams.topMargin + cParams.bottomMargin;
            }

        }
        //如果是需要计算的话    得到的ViewGroup的高度
        int width = Math.max(topWidth, bottomWidth);
        int height = Math.max(leftHeight, rightHeight);

        int sureWidth = width;//真正的width  默认是 wrap_content 时的值
        int sureHeight = height; //真正的height; 默认是 wrap_content 时的值

        if (widthMode == MeasureSpec.EXACTLY) {
            // 固定的值 或者是 MATH_CONTENT  表示此ViewGroup的模式是  固定或者是 MATCH_CONTENT
            //设置测量的结果
            sureWidth = sizeWidth;
        }


        if (heightMode == MeasureSpec.EXACTLY) {
            // 固定的值 或者是 MATH_CONTENT  表示此ViewGroup的模式是  固定或者是 MATCH_CONTENT
            //设置测量的结果
            sureHeight = sizeHeight;
        }

        //进行赋值
        // 当测量模式是  EXACTLY  表示是 固定的值  或者 MATH_CONTENT 时  使用测量的 size
        // 如果测量的模式是 AS_MOST 表示是 wrap_content   使用计算好的  size
        setMeasuredDimension(sureWidth, sureHeight);


    }


    /**
     * onLayout对其所有childView进行定位（设置childView的绘制区域）
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        //将 子View  layout
        int count = getChildCount();

        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            cWidth = childView.getMeasuredWidth();
            cHeight = childView.getMeasuredHeight();
            cParams = (MarginLayoutParams) childView.getLayoutParams();

            int cleft = 0, ctop = 0, cright = 0, cbottom = 0;
            switch (i) {
                case 0:
                    cleft = cParams.leftMargin;
                    ctop = cParams.topMargin;
                    break;
                case 1:
                    cleft = getWidth() - cWidth - cParams.rightMargin;
                    ctop = cParams.topMargin;
                    break;
                case 2:
                    cleft = cParams.leftMargin;
                    ctop = getHeight() - cHeight - cParams.bottomMargin;
                    break;
                case 3:
                    cleft = getWidth() - cWidth - cParams.rightMargin;
                    ctop = getHeight() - cHeight - cParams.bottomMargin;
                    break;
                default:
                    break;


            }

            cright = cWidth + cleft;
            cbottom = cHeight + ctop;
            childView.layout(cleft, ctop, cright, cbottom);
        }

    }

    /**
     * 我们只需要ViewGroup能够支持margin即可，那么我们直接使用系统的MarginLayoutParams
     */
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
