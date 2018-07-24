package com.example.mydemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * author : 宋佳
 * time   : 2018/07/24
 * desc   : 自定义 微博的  scrollview
 * version: 1.0.0
 */

public class WeiBoScrollView extends ScrollView {

    public OnScrollViewListener onScrollViewListener;

    public WeiBoScrollView(Context context) {
        super(context);
    }

    public WeiBoScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeiBoScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setOnScrollViewListener(OnScrollViewListener onScrollViewListener) {
        this.onScrollViewListener = onScrollViewListener;
    }

    public interface OnScrollViewListener {
        void onSrcollChange(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        onScrollViewListener.onSrcollChange(l, t, oldl, oldt);
    }
}
