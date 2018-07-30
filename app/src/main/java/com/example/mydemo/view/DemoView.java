package com.example.mydemo.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
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

public class DemoView extends LinearLayout implements Animator.AnimatorListener {


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

    private AnimationDrawable animLike, animDis; //笑脸帧动画


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
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setOrientation(LinearLayout.HORIZONTAL);


        //笑脸 View  并设置帧动画
        likeImage = new ImageView(getContext());
        likeImage.setImageResource(R.drawable.animation_like);
        animLike = (AnimationDrawable) likeImage.getDrawable();


        disLikeImage = new ImageView(getContext());
        disLikeImage.setImageResource(R.drawable.animation_dislike);
        animDis = (AnimationDrawable) disLikeImage.getDrawable();


        likeBack = new LinearLayout(getContext());
        likeBack.setOrientation(LinearLayout.VERTICAL);
        likeBackParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        likeBack.setBackgroundResource(R.drawable.shape_white_background);
        likeBack.addView(likeImage, likeBackParams);


        disLikeBack = new LinearLayout(getContext());
        disLikeBack.setOrientation(LinearLayout.VERTICAL);
        disLikeBackParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        disLikeBack.addView(disLikeImage, disLikeBackParams);
        disLikeBack.setBackgroundResource(R.drawable.shape_white_background);


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


        //设置百分比
        //设置百分比
        float count = like + disLike;
        float fLike = like / count;
        float fDis = disLike / count;
        like = (int) (fLike * 100);
        disLike = (int) (fDis * 100);


    }

    private void initListener() {

        likeImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击笑脸的时候
                likeBack.setBackgroundResource(R.drawable.shape_yellow_background);
                animBack();


            }
        });

        disLikeImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                disLikeBack.setBackgroundResource(R.drawable.shape_yellow_background);
                animBack();


            }
        });

    }


    //拉伸背景
    private void animBack() {
        likeImage.setEnabled(false);
        disLikeImage.setEnabled(false);

        int max = Math.max(like * 4, disLike * 4);

        //进行拉伸的动画
        animatorBack = ValueAnimator.ofInt(5, max);

        animatorBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //区间值的变化
                int value = (int) animation.getAnimatedValue();

                LayoutParams layoutParams = (LayoutParams) likeImage.getLayoutParams();
                layoutParams.bottomMargin = value;

                if (value <= like * 4) {
                    likeImage.setLayoutParams(layoutParams);
                }
                if (value < disLike * 4) {
                    disLikeImage.setLayoutParams(layoutParams);
                }
            }
        });

        animatorBack.setDuration(500);
        animatorBack.addListener(this);
        animatorBack.start();

    }


    //回弹背景
    private void setBackUp() {
        final int max = Math.max(like * 4, disLike * 4);
        animatorBack = ValueAnimator.ofInt(max, 5);
        animatorBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int magrin = (int) animation.getAnimatedValue();
                LayoutParams paramsLike
                        = (LayoutParams) likeImage.getLayoutParams();
                paramsLike.bottomMargin = magrin;

                if (magrin <= like * 4) {
                    likeImage.setLayoutParams(paramsLike);
                }
                if (magrin <= disLike * 4) {
                    disLikeImage.setLayoutParams(paramsLike);
                }
            }
        });
        animatorBack.addListener(this);
        animatorBack.setDuration(500);
        animatorBack.start();
    }


    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

        //重置帧动画
        animDis.stop();
        animLike.stop();

        //当动画完成的时候
        animLike.start();
        animDis.start();

        //进行脸部的动画
        objectY(likeImage);
        //进行哭脸的动画
        objectX(disLikeImage);

    }

    //上下的一个动画   这个是笑脸的
    private void objectY(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", -10.0f, 0.0f, 10.0f, 0.0f, -10.0f, 0.0f, 10.0f, 0);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        //animator.setRepeatCount(1);
        animator.setDuration(1500);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                setBackUp();
            }
        });
    }


    //左右的一个动画 这个是哭脸的
    private void objectX(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationX", -10.0f, 0.0f, 10.0f, 0.0f, -10.0f, 0.0f, 10.0f, 0);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        //animator.setRepeatCount(1);
        animator.setDuration(1500);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                setBackUp();
            }
        });
    }


    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}



