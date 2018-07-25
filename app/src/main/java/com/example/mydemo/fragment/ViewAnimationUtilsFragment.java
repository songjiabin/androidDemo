package com.example.mydemo.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/07/24
 * desc   :
 * version: 1.0.0
 */

public class ViewAnimationUtilsFragment extends Fragment {


    @LayoutRes
    int sampleLayoutRes;
    private ImageView image;

    public static ViewAnimationUtilsFragment newInstance(@LayoutRes int sampleLayoutRes) {
        ViewAnimationUtilsFragment fragment = new ViewAnimationUtilsFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewanimation_utils, container, false);
        image = (ImageView) view.findViewById(R.id.image);
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demo1();
            }
        });
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demo2();
            }
        });

        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demo3();
            }
        });

        return view;
    }


    /**
     * 第一个参数代表 操作的view
     * 第二个参数代表 圆的x方向的中点
     * 第三个参数代表 圆的y方向的中点
     * 第四个参数代表 圆开始时候的半径
     * 第五个参数代表 结束时候的半径
     */
    // 斜线展示
    public void demo1() {
        Animator animator1 = ViewAnimationUtils.createCircularReveal(image, 0, 0, 0, (float) Math.hypot(image.getWidth(), image.getHeight()));
        animator1.setInterpolator(new LinearInterpolator());//插补器有没有不影响
        animator1.setDuration(2000);
        animator1.start();
    }


    // 向外揭露
    public void demo2() {
        int cenX = image.getWidth() / 2;
        int cenY = image.getHeight() / 2;
        Animator an = ViewAnimationUtils.createCircularReveal(image, cenX, cenY, 0, cenX);
        an.setDuration(3000);
        an.start();
        an.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                image.setVisibility(View.VISIBLE);
            }
        });
    }



    // 由外向内揭露
    public void demo3() {
        int centerX = image.getWidth() / 2;//获取组件的宽的一半
        int centerY = image.getHeight() / 2;//获取组件的高的一半
        Animator animator = ViewAnimationUtils.createCircularReveal(image, centerX, centerY,image.getWidth(), 0);
        animator.setDuration(3000);
        animator.setInterpolator(new LinearOutSlowInInterpolator());//out到in
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                image.setVisibility(View.VISIBLE);
            }
        });
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
