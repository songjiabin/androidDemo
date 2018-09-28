package com.example.mydemo.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * author : 宋佳
 * time   : 2018/09/27
 * desc   :  自定义ViewGroup 需求：我们定义一个ViewGroup，内部可以传入0到4个childView，分别依次显示在左上角，右上角，左下角，右下角。
 * version: 1.0.0
 */


public class OtherViewGroup_fourCorners extends ViewGroup {


    public OtherViewGroup_fourCorners(Context context) {
        super(context);
    }

    public OtherViewGroup_fourCorners(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OtherViewGroup_fourCorners(Context context, AttributeSet attrs, int defStyleAttr) {
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


        int childCount = getChildCount();
        if (childCount == 0) {
            //说明没有子View 。那么当前ViewGroup没有存在的意思。不用占用
            setMeasuredDimension(0, 0);
        } else {
            MarginLayoutParams cParams = null;
            int topWidth = 0;//上面第一行宽度
            int bottomWidth = 0;//下面一行的宽度

            int leftHeight = 0; //左边一列的高度
            int rightHeight = 0;//右边一列的高度

            int measureWidth = 0;
            int measureHeight = 0;

            for (int i = 0; i < getChildCount(); i++) {
                View view = getChildAt(i);
                cParams = (MarginLayoutParams) view.getLayoutParams();
                measureWidth = view.getMeasuredWidth();
                measureHeight = view.getMeasuredHeight();
                if (i == 0 || i == 1) {
                    topWidth += measureWidth + cParams.leftMargin + cParams.rightMargin;
                }
                if (i == 2 || i == 3) {
                    bottomWidth += measureWidth + cParams.leftMargin + cParams.rightMargin;
                }
                if (i == 0 || i == 2) {
                    leftHeight += measureHeight + cParams.topMargin + cParams.bottomMargin;
                }
                if (i == 1 || i == 3) {
                    rightHeight += measureHeight + cParams.topMargin + cParams.bottomMargin;
                }


            }


            int maxWidth = Math.min(Math.max(topWidth, bottomWidth), sizeWidth);
            int maxHeigth = Math.min(Math.max(leftHeight, rightHeight), sizeHeight);
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                // 最大的值是限定的
                setMeasuredDimension(maxWidth, maxHeigth);
            } else if (widthMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(maxWidth, sizeHeight);
            } else if (heightMode == MeasureSpec.AT_MOST) {
                setMeasuredDimension(sizeWidth, maxHeigth);
            }


        }


    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int viewGroupWidth = this.getMeasuredWidth();
        int viewGroupHeight = this.getMeasuredHeight();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            MarginLayoutParams cParams = (MarginLayoutParams) view.getLayoutParams();
            switch (i) {

                case 0:
                    view.layout(cParams.leftMargin, cParams.topMargin, view.getMeasuredWidth() + cParams.leftMargin, view.getMeasuredHeight() + cParams.topMargin);
                    break;
                case 1:
                    //右上角
                    view.layout(viewGroupWidth - cParams.rightMargin - view.getMeasuredWidth(), cParams.topMargin, viewGroupWidth - cParams.rightMargin, cParams.topMargin + view.getMeasuredHeight());
                    break;
                case 2:
                    //左下角
                    view.layout(cParams.leftMargin, viewGroupHeight - cParams.bottomMargin - view.getMeasuredHeight(), cParams.leftMargin + view.getMeasuredWidth(), viewGroupHeight - cParams.bottomMargin);
                    break;
                case 3:
                    //右下角
                    view.layout(viewGroupWidth - cParams.rightMargin - view.getMeasuredWidth(), viewGroupHeight - cParams.bottomMargin - view.getMeasuredHeight(), viewGroupWidth - cParams.rightMargin, viewGroupHeight - cParams.bottomMargin);
                    break;
                default:
                    break;
            }
        }


       /*//将 子View  layout
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
*/

    }


    /**
     * 我们只需要ViewGroup能够支持margin即可，那么我们直接使用系统的MarginLayoutParams
     */
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
