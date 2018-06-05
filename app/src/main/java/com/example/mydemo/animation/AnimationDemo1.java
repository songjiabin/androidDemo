package com.example.mydemo.animation;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;

/**
 * time   : 2018/06/05
 * desc   : 视图动画
 * version: 1.0.0
 */

public class AnimationDemo1 extends View {


    private Context context;

    public AnimationDemo1(Context context) {
        super(context);
        initView(context);
    }

    public AnimationDemo1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AnimationDemo1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;

        //透明度
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1000);
        this.startAnimation(alphaAnimation);
    }


}
