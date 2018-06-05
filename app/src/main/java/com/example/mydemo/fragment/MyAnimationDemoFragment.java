package com.example.mydemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/06/05
 * desc   :
 * version: 1.0.0
 */

public class MyAnimationDemoFragment extends Fragment {


    @LayoutRes
    int sampleLayoutRes;
    private AnimationSet set;

    public static MyAnimationDemoFragment newInstance(@LayoutRes int sampleLayoutRes) {
        MyAnimationDemoFragment fragment = new MyAnimationDemoFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_my_animation, container, false);
        final TextView textView_demo = (TextView) view.findViewById(R.id.textView_demo);

        set = new AnimationSet(true);


        view.findViewById(R.id.btn_alpha).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //透明度
                AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
                alphaAnimation.setDuration(1000);
                textView_demo.setAnimation(alphaAnimation);

                set.addAnimation(alphaAnimation);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        //动画开始的时候
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        //动画结束的时候
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        //动画重复的时候
                    }
                });
            }
        });

        view.findViewById(R.id.btn_rotate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //旋转

                // fromDegrees 从多少度开始旋转
                // toDegrees   旋转到多少度
                // pivotX  pivotY 旋转中心点的坐标点


                RotateAnimation rotateAnimation = new RotateAnimation(0, 180, textView_demo.getWidth() / 2, textView_demo.getHeight() / 2);

                //设置旋转的参考系  为中心
                RotateAnimation rotateAnimation1 = new RotateAnimation(0, 90, RotateAnimation.RELATIVE_TO_SELF, 0.5F, RotateAnimation.RELATIVE_TO_SELF, 0.5F);

                rotateAnimation1.setDuration(1000);
                textView_demo.startAnimation(rotateAnimation1);

                set.addAnimation(rotateAnimation1);
            }
        });
        view.findViewById(R.id.btn_translate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //平移
                TranslateAnimation translateAnimation = new TranslateAnimation(0, 200, 0, 300);
                translateAnimation.setDuration(1000);
                textView_demo.startAnimation(translateAnimation);
                set.addAnimation(translateAnimation);
            }
        });
        view.findViewById(R.id.btn_scale).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //缩放
                ScaleAnimation scaleAnimation = new ScaleAnimation(0, 2, 0, 2);

                //设置参考点为中心
                ScaleAnimation sa1 = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5F, Animation.RELATIVE_TO_SELF, 0.5F);
                sa1.setDuration(1000);
                textView_demo.startAnimation(sa1);
                set.addAnimation(sa1);
            }
        });
        view.findViewById(R.id.btn_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                set.setDuration(2000);
//                textView_demo.startAnimation(set);



                Animation translateAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.view_animation);
                // 步骤2:创建 动画对象 并传入设置的动画效果xml文件
                textView_demo.startAnimation(translateAnimation);
            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            sampleLayoutRes = args.getInt("sampleLayoutRes");

        }
    }
}
