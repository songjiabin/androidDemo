package com.example.mydemo.removeview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * author : 宋佳
 * time   : 2018/08/21
 * desc   :
 * version: 1.0.0
 */

public class ScrollerView extends View {

    private Scroller scroller;

    public ScrollerView(Context context) {
        super(context);
        initView();
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ScrollerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    private void initView() {
        scroller = new Scroller(getContext());
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断Scroller是否执行完毕
        if (scroller.computeScrollOffset()) {
            ViewGroup viewGroup = (ViewGroup) this.getParent();
            int x =scroller.getCurrX();
            int y =scroller.getCurrY();
            viewGroup.scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    public void smoothScrollTo(int destX, int destY) {

        View viewGroup =  ((View) getParent());

        int scrollX = this.getScrollX();
        int scrollY = this.getScrollY();
        //偏移量
        int deltaX = destX - scrollX;
        int deltaY = destY - scrollY;
        scroller.startScroll(scrollX, scrollY, deltaX, deltaY, 2000);
    }


}
