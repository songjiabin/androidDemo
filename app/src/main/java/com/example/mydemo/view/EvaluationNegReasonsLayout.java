package com.example.mydemo.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/07/18
 * desc   :  自定义差评理由选择 view       当用户给出差评的时候，需要展示对应的差评理由选择。理由云控，数量可变，内容可变。可单选，可不选，可多选。
 * <p>
 * 主要的难点和重点在于根据理由内容的长短进行展示，如果内容长则显示一条，如果内容短可以显示多条。
 * <p>
 * version: 1.0.0
 */

public class EvaluationNegReasonsLayout extends ViewGroup {


    /**
     * 检测数据变化的观察者
     */
    private AdapterDataSetObserver mDataSetObserver;
    /**
     * 适配器
     */
    private BaseAdapter mAdapter;


    /**
     * 存储被选择的 view
     */
    private SparseBooleanArray mCheckedReasonArray = new SparseBooleanArray();
    private OnReasonSelectListener mOnReasonSelectListener;


    public EvaluationNegReasonsLayout(Context context) {
        super(context);
    }

    public EvaluationNegReasonsLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EvaluationNegReasonsLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int realWidht = getWidth();
        int childLeft = 0;
        int childTop = 0;


        //遍历子控件，记录每个子view的位置
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View childView = getChildAt(i);


            //跳过View.GONE的子View
            if (childView.getVisibility() == View.GONE) {
                continue;
            }


            //获取到测量的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();


            //因为子View可能设置margin，这里要加上margin的距离
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();


            if (childLeft + mlp.leftMargin + childWidth + mlp.rightMargin > realWidht) {
                //换行处理
                childTop += (mlp.topMargin + childHeight + mlp.bottomMargin);
                childLeft = 0;
            }

            //布局
            int left = childLeft + mlp.leftMargin;
            int top = childTop + mlp.topMargin;
            int right = childLeft + mlp.leftMargin + childWidth;
            int bottom = childTop + mlp.topMargin + childHeight;
            childView.layout(left, top, right, bottom);
            childLeft += (mlp.leftMargin + childWidth + mlp.rightMargin);


        }


    }


    /**
     * 我们都知道 View 的测量工作主要是在 onMeasure 里进行。
     * 宽度计算，可以先测量出每个子 View 的宽度，每次叠加，如果超过父布局限制的宽度则换行。
     * 高度计算，每次换行叠加高度，每一行的高度取子 View 高度的最大值。
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // 获取Padding
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);


        // NegativeReasonsLayout 最终的宽度和高度值
        int resultWidth = 0;
        int resultHeight = 0;

        //测量时每一行的宽度（每个View 相加的宽度 ）
        int lineWidth = 0;
        //测量时每一行的高度，加起来就是 NegativeReasonsLayout 的高度
        int lineHeight = 0;


        //遍历每个子元素
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View childView = getChildAt(i);
            //测量每一个子view的宽和高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //获取到测量的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            //因为子View可能设置margin，这里要加上margin的距离
            MarginLayoutParams mlp = (MarginLayoutParams) childView.getLayoutParams();
            int realChildWidth = childWidth + mlp.leftMargin + mlp.rightMargin; //真正的 child的宽
            int realChildHeight = childHeight + mlp.topMargin + mlp.bottomMargin; //真正的 child的高


            //如果当前一行的宽度加上要加入的子view的宽度大于父容器给的宽度，就换行
            if ((lineWidth + realChildWidth) > sizeWidth) {
                //换行
                resultWidth = Math.max(lineWidth, realChildWidth);
                resultHeight += lineHeight;
                //换行了，lineWidth和lineHeight重新算
                lineWidth = realChildWidth;
                lineHeight = realChildHeight;

            } else {
                //不换行 ， 行高 直接相加
                lineWidth += resultWidth;
                //每一行的高度取二者最大值
                lineHeight = Math.max(lineHeight, realChildHeight);
            }


            // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较
            //遍历到最后一个的时候，肯定走的是不换行
            if (i == childCount - 1) {
                resultWidth = Math.max(lineWidth, resultWidth);
                resultHeight += lineHeight;
            }
        }
        setMeasuredDimension(modeWidth == MeasureSpec.EXACTLY ? sizeWidth : resultWidth, modeHeight == MeasureSpec.EXACTLY ? sizeHeight : resultHeight);

    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }


    // 数据的观察者   模式
    class AdapterDataSetObserver extends DataSetObserver {


        @Override
        public void onChanged() {
            super.onChanged();
            //数据变化的时候
            reloadData();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }


    //加载数据
    private void reloadData() {
        removeAllViews();


        for (int i = 0; i < mAdapter.getCount(); i++) {
            final int j = i;
            mCheckedReasonArray.put(i, false);
            final View childView = mAdapter.getView(i, null, this);




            addView(childView, new MarginLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)));

            if (mAdapter instanceof OnInitSelectedPosition) {
                boolean isSelected = ((OnInitSelectedPosition) mAdapter).isSelectedPosition(i);
                if (isSelected) {
                    mCheckedReasonArray.put(i, true);
                    childView.setSelected(true);
                }
            }


            childView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCheckedReasonArray.get(j)) {
                        mCheckedReasonArray.put(j, false);
                        childView.setSelected(false);
                    } else {
                        mCheckedReasonArray.put(j, true);
                        childView.setSelected(true);
                    }

                    //回调
                    if (mOnReasonSelectListener != null) {
                        List<Integer> list = new ArrayList<>();
                        for (int k = 0; k < mAdapter.getCount(); k++) {
                            if (mCheckedReasonArray.get(k)) {
                                list.add(k);
                            }
                        }
                        mOnReasonSelectListener.onItemSelect(EvaluationNegReasonsLayout.this, list);
                    }
                }
            });


        }


    }


    public ListAdapter getAdapter() {
        return mAdapter;
    }

    public void setAapter(BaseAdapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }


        //清除现有的数据
        removeAllViews();

        mAdapter = adapter;


        if (mAdapter != null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }


    }


    public void setOnReasonSelectListener(OnReasonSelectListener onReasonSelectListener) {
        this.mOnReasonSelectListener = onReasonSelectListener;
    }


    public interface OnReasonSelectListener {
        void onItemSelect(EvaluationNegReasonsLayout parent, List<Integer> selectedList);
    }


    public interface OnInitSelectedPosition {
        boolean isSelectedPosition(int position);
    }


}
