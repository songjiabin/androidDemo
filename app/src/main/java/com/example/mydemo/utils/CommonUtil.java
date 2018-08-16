package com.example.mydemo.utils;

import android.view.View;

/**
 * author : 宋佳
 * time   : 2018/08/14
 * desc   :
 * version: 1.0.0
 */

public class CommonUtil {
    /**
     * 测量View的宽高
     *
     * @param view View
     */
    public static void measureWidthAndHeight(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
    }
}
