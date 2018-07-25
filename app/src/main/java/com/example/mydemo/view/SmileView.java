package com.example.mydemo.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.utils.ScreenUtils;

/**
 * author : 宋佳
 * time   : 2018/07/25
 * desc   : 自定义的一个笑脸的 View
 * version: 1.0.0
 */

public class SmileView extends LinearLayout implements Animator.AnimatorListener {

    private int defalutGravity = Gravity.CENTER_HORIZONTAL;

    private int like = 10;
    private int disLike = 20;
    private float fLike;
    private float fDis;
    private ImageView imageLike;
    private AnimationDrawable animLike, animDis; //笑脸帧动画
    private TextView likeNum;

    private int defalutTextColor = Color.WHITE;
    private TextView likeText;


    private String defaultLike = "喜欢";
    private String defalutDis = "无感";
    private ImageView imageDis;
    private TextView disNum;
    private TextView disText;
    private LinearLayout likeBack;
    private LinearLayout disBack;
    private int defalutSize = ScreenUtils.dp2px(getContext(), 25);
    private LinearLayout likeAll;
    private LinearLayout disAll;


    private int dividerMargin = 20;
    private int defalutBottom = 70;

    private boolean isClose = false; //判断收起动画

    private int type = 0; //选择执行帧动画的笑脸 //0 笑脸 1 哭脸
    private ValueAnimator animatorBack;


    public SmileView(Context context) {
        super(context);
        initView(context);
    }

    public SmileView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initListener();
    }


    private void initView(Context context) {
        this.removeAllViews();
        setOrientation(HORIZONTAL);
        setGravity(defalutGravity | Gravity.BOTTOM);
        //设置整体透明
        this.setBackgroundColor(Color.TRANSPARENT);

        //设置百分比
        //设置百分比
        float count = like + disLike;
        fLike = like / count;
        fDis = disLike / count;
        like = (int) (fLike * 100);
        disLike = (int) (fDis * 100);


        //喜欢的
        imageLike = new ImageView(getContext());
        //添加动画资源  获得帧动画
        imageLike.setBackgroundResource(R.drawable.animation_like);
        animLike = (AnimationDrawable) imageLike.getBackground();
        //初始化文字
        likeNum = new TextView(getContext());
        likeNum.setText(like + "%");
        likeNum.setTextColor(defalutTextColor);
        TextPaint likeNumPaint = likeNum.getPaint();
        //是否加粗
        likeNumPaint.setFakeBoldText(true);
        likeNum.setTextSize(20f);
        likeText = new TextView(getContext());
        likeText.setText(defaultLike);
        likeText.setTextColor(defalutTextColor);


        //不喜欢的
        imageDis = new ImageView(getContext());
        imageDis.setBackgroundResource(R.drawable.animation_dislike);
        animDis = (AnimationDrawable) imageDis.getBackground();
        disNum = new TextView(getContext());
        disNum.setText(disLike + "%");
        disNum.setTextColor(defalutTextColor);
        TextPaint disNumPaint = disNum.getPaint();
        disNumPaint.setFakeBoldText(true);
        disNum.setTextSize(20f);
        disText = new TextView(getContext());
        disText.setText(defalutDis);
        disText.setTextColor(defalutTextColor);


        //初始化布局
        likeBack = new LinearLayout(getContext());
        disBack = new LinearLayout(getContext());
        LayoutParams params2 = new LayoutParams(defalutSize, defalutSize);
        likeBack.addView(imageLike, params2);
        disBack.addView(imageDis, params2);
        disBack.setBackgroundResource(R.drawable.shape_white_background);
        likeBack.setBackgroundResource(R.drawable.shape_white_background);


        //单列总布局
        likeAll = new LinearLayout(getContext());
        disAll = new LinearLayout(getContext());
        likeAll.setOrientation(VERTICAL);
        disAll.setOrientation(VERTICAL);
        likeAll.setGravity(Gravity.CENTER_HORIZONTAL);
        disAll.setGravity(Gravity.CENTER_HORIZONTAL);
        likeAll.setBackgroundColor(Color.TRANSPARENT);
        disAll.setBackgroundColor(Color.TRANSPARENT);


        //添加文字图片放进一列
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 0);
        params.gravity = Gravity.CENTER;
        disAll.setGravity(Gravity.CENTER_HORIZONTAL);
        likeAll.setGravity(Gravity.RIGHT);
        disAll.addView(disNum, params);
        disAll.addView(disText, params);
        disAll.addView(disBack, params);


        likeAll.addView(likeNum, params);
        likeAll.addView(likeText, params);
        likeAll.addView(likeBack, params);


        //中间的分割线
        ImageView imageView = new ImageView(getContext());
        imageView.setBackground(new ColorDrawable(Color.GRAY));
        LayoutParams params4 = new LayoutParams(3, 80);
        params4.setMargins(dividerMargin, 10, dividerMargin, defalutBottom + 20);
        params4.gravity = Gravity.BOTTOM;


        //整个布局的 params
        LayoutParams params3 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params3.setMargins(30, 20, 30, defalutBottom);
        params3.gravity = Gravity.BOTTOM;
        addView(disAll, params3);
        addView(imageView, params4);
        addView(likeAll, params3);


        //隐藏文字
        setVisibities(GONE);
    }


    public void setVisibities(int v) {
        likeNum.setVisibility(v);
        disNum.setVisibility(v);
        likeText.setVisibility(v);
        disText.setVisibility(v);
    }


    private void initListener() {
        imageDis.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                type = 1; //设置动画对象 //选择折行 哭脸
                animBack(); //拉伸背景
            }
        });
        imageLike.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void animBack() {
        //动画执行中不能点击
        imageDis.setClickable(false);
        imageLike.setClickable(false);


        final int max = Math.max(like * 4, disLike * 4);
        animatorBack = ValueAnimator.ofInt(5, max);
        animatorBack.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int magrin = (int) animation.getAnimatedValue();
                LayoutParams paramsLike
                        = (LayoutParams) imageLike.getLayoutParams();
                paramsLike.bottomMargin = magrin;

                if (magrin <= like * 4) {
                    imageLike.setLayoutParams(paramsLike);
                }
                if (magrin <= disLike * 4) {
                    imageDis.setLayoutParams(paramsLike);
                }
            }
        });
        isClose = false;
        animatorBack.addListener(this);
        animatorBack.setDuration(500);
        animatorBack.start();
    }


    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
