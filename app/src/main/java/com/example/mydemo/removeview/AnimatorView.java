package com.example.mydemo.removeview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : 宋佳
 * time   : 2018/08/20
 * desc   :
 * version: 1.0.0
 */

public class AnimatorView extends View {

    public int widths;


    public AnimatorView(Context context) {
        super(context);
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public int getWidths() {
        return widths;
    }

    public void setWidths(int width) {

        this.getLayoutParams().width = width;
        //刷新 控件
        this.requestLayout();
    }


}
