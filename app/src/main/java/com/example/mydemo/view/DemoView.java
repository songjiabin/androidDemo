package com.example.mydemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/07/27
 * desc   :
 * version: 1.0.0
 */

public class DemoView extends LinearLayout {


    private ImageView likeImage;
    private ImageView disLikeImage;
    private LinearLayout likeBack;
    private LinearLayout disLikeBack;
    private LayoutParams likeBackParams;
    private LayoutParams disLikeBackParams;
    private LayoutParams allParams;
    private ImageView imageLine;

    private int like = 10;
    private int disLike = 20;
    private ValueAnimator animatorBack;

    public DemoView(Context context) {
        super(context);
        initView();
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        initListener();
    }


    private void initView() {
        this.removeAllViews();
        this.setOrientation(LinearLayout.HORIZONTAL);


        likeImage = new ImageView(getContext());
        likeImage.setBackgroundResource(R.drawable.like_1);


        disLikeImage = new ImageView(getContext());
        disLikeImage.setBackgroundResource(R.drawable.dislike_1);


        likeBack = new LinearLayout(getContext());
        likeBack.setOrientation(LinearLayout.VERTICAL);
        likeBackParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        likeBack.addView(likeImage, likeBackParams);


        disLikeBack = new LinearLayout(getContext());
        disLikeBack.setOrientation(LinearLayout.VERTICAL);
        disLikeBackParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        disLikeBack.addView(disLikeImage, disLikeBackParams);


        allParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        allParams.gravity = Gravity.BOTTOM;
        allParams.setMargins(30, 20, 30, 100);
        likeBack.setGravity(Gravity.CENTER_HORIZONTAL);
        disLikeBack.setGravity(Gravity.CENTER_HORIZONTAL);
        this.setGravity(Gravity.CENTER_HORIZONTAL);


        //画线
        imageLine = new ImageView(getContext());
        imageLine.setBackground(new ColorDrawable(Color.GRAY));
        LayoutParams lineParams = new LayoutParams(3, 80);
        lineParams.gravity = Gravity.BOTTOM;
        lineParams.setMargins(0, 0, 0, 100);

        this.addView(likeBack, allParams);
        this.addView(imageLine, lineParams);
        this.addView(disLikeBack, allParams);


    }

    private void initListener() {

        likeImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                likeBack.setBackgroundResource(R.drawable.shape_yellow_background);
                animBack();
            }
        });

        disLikeImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }


    //拉伸背景
    private void animBack() {
        likeImage.setEnabled(false);
        disLikeImage.setEnabled(false);

        int max = Math.max(like * 4, disLike * 4);

        animatorBack = ValueAnimator.ofInt(0, max);

        animatorBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //区间值的变化
                int value = (int) animation.getAnimatedValue();

                LayoutParams layoutParams = (LayoutParams) likeImage.getLayoutParams();
                layoutParams.bottomMargin = value;

                likeImage.setLayoutParams(layoutParams);



            }
        });

        animatorBack.setDuration(500);
        animatorBack.start();

    }


}



