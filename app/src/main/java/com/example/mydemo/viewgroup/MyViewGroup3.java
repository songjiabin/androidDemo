package com.example.mydemo.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * author : 宋佳
 * time   : 2018/09/26
 * desc   : 实现一个类似于 LinearLayout  垂直布局的 demo
 * version: 1.0.0
 */

public class MyViewGroup3 extends ViewGroup {


    private Context context;

    public MyViewGroup3(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyViewGroup3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    private void initView(Context context) {
        this.context = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将所有的子View进行测量，这会触发每个子View的onMeasure函数

        //注意要与measureChild区分，measureChild是对单个view进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);


        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int childCount = getChildCount();

        if (childCount == 0) {
            //说明没有子View 。那么当前ViewGroup没有存在的意思。不用占用
            setMeasuredDimension(0, 0);

        } else {

            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
                //如果 ViewGroup 宽高 都是 wrap_content

                //我们将高度设置为所有子View的高度相加，宽度设为子View中最大的宽度
                setMeasuredDimension(Math.min(getMaxWidth(), widthSize), Math.min(getAllViewsHeight(), heightSize));

            } else if (widthMode == MeasureSpec.AT_MOST) {
                //即：宽度是 wrap_content 高度是 固定的值


                // 设置  宽度的最大值 不能超过  withSize    高度是固定的  heightSize

                setMeasuredDimension(Math.min(getMaxWidth(), widthSize), heightSize);

            } else if (heightMode == MeasureSpec.AT_MOST) {
                //即：高度是 wrap_content 宽度是 固定的值


                // 设置  高度是 叠加的  宽度是 固定的

                setMeasuredDimension(widthSize, Math.min(getAllViewsHeight(), heightSize));
            }
        }
    }

    /**
     * 得到  子View 中 最大的宽度
     *
     * @return
     */
    private int getMaxWidth() {
        int maxWidth = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view.getMeasuredWidth() > maxWidth) {
                maxWidth = view.getMeasuredWidth();
            }
        }
        return maxWidth;
    }


    /**
     * 得到所有子View的高度
     *
     * @return
     */
    private int getAllViewsHeight() {
        int totalHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            totalHeight += view.getMeasuredHeight();
        }
        return totalHeight;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        //记录当前的高度位置

        // 注意 curHeight 应为0开始。不能等于 t 。t是此 ViewGroup 相对于它父容器的距离。
        // 当 此ViewGroup 并不是父容器的第一个子View的时候。就会出现问题。所以 curHeight 应为 0 开始
        int curHeight = 0;


        //将子View逐个摆放
        for (int i = 0; i < count; i++) {
            View view = getChildAt(i);
            int height = view.getMeasuredHeight();
            int width = view.getMeasuredWidth();

            view.layout(l, curHeight, l + width, curHeight + height);
            curHeight += height;

        }

    }
}
