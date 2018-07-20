package com.example.mydemo.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.mydemo.R;
import com.example.mydemo.utils.ScreenUtils;

/**
 * author : 宋佳
 * time   : 2018/07/18
 * desc   :  自定义  客服评价
 * version: 1.0.0
 */

public class EvaluationRatingBar extends LinearLayout {


    //评级星星的个数
    private int mStarTotal = 5;
    /**
     * 此布局的高度
     */
    private float mHeight;

    /**
     * 间隔宽度
     */
    private float mIntervalWidth = ScreenUtils.dp2px(this.getContext(), 10f);

    /**
     * 星星对应的资源 id
     */
    private int mStarResId = -1;

    /**
     * 星星被选中的数量
     */
    private int mSelectedCount = 2;

    /**
     * 是否可被编辑
     */
    private boolean mEditable = true;


    public EvaluationRatingBar(Context context) {
        super(context);
    }

    public EvaluationRatingBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EvaluationRatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initView(Context context) {
        //移除所有的子View
        removeAllViews();
        for (int i = 0; i < mStarTotal; i++) {
            //因为星星是可以点击变灰色 变亮色  所以使用 checkBox
            CheckBox cb = new CheckBox(getContext());
            LayoutParams layoutParams;
            if (mHeight == 0) {
                layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            } else {
                layoutParams = new LayoutParams((int) mHeight, (int) mHeight);
            }
            //设置内容的排列方式为垂直居中
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            if (i != 0 && i != mStarTotal - 1) {
                //如果不是第一个和最后一个 那么设置 左边距 和 右边距
                layoutParams.leftMargin = (int) mIntervalWidth;
                layoutParams.rightMargin = (int) mIntervalWidth;
            } else if (i == 0) {
                layoutParams.rightMargin = (int) mIntervalWidth;
            } else if (i == mStarTotal - 1) {
                layoutParams.leftMargin = (int) mIntervalWidth;
            }
            //将 checkBox 添加到  布局中去
            addView(cb, layoutParams);
            //设置 checkBox中 前面默认的 圈为空
            cb.setButtonDrawable(new ColorDrawable(getResources().getColor(android.R.color.transparent)));
            if (mStarResId == -1) {
                mStarResId = R.drawable.selector_rating_bar_default;
            }
            cb.setBackgroundResource(mStarResId);
            if (i + 1 <= mSelectedCount) {
                //设置默认的 显示 亮色 的星星
                cb.setChecked(true);
            }
            //设置是否可点击
            cb.setEnabled(mEditable);
            cb.setOnClickListener(new MyClickListener(i));
        }

    }

    private class MyClickListener implements OnClickListener {

        private int position;

        public MyClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            mSelectedCount = position + 1;
            for (int i = 0; i < mStarTotal; i++) {
                CheckBox cb = (CheckBox) getChildAt(i);
                if (i <= position) {
                    cb.setChecked(true);
                } else if (i > position) {
                    cb.setChecked(false);
                }
            }

            if (mOnRatingChangeListener != null) {
                mOnRatingChangeListener.onChange(mSelectedCount);
            }
        }
    }


    private OnRatingChangeListener mOnRatingChangeListener;

    public void setOnRatingChangeListener(OnRatingChangeListener onRatingChangeListener) {
        mOnRatingChangeListener = onRatingChangeListener;
    }

    public interface OnRatingChangeListener {
        /**
         * @param electedCount 星星选中的个数
         */
        void onChange(int electedCount);
    }


}
