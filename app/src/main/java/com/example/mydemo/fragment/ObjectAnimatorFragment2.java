package com.example.mydemo.fragment;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydemo.R;
import com.example.mydemo.evaluator.ColorEvaluator;
import com.example.mydemo.view.MyObjectAnimationView;

/**
 * author : 宋佳
 * time   : 2018/06/14
 * desc   :
 * version: 1.0.0
 */

public class ObjectAnimatorFragment2 extends Fragment {

    @LayoutRes
    int sampleLayoutRes;

    public static ObjectAnimatorFragment2 newInstance(@LayoutRes int sampleLayoutRes) {
        ObjectAnimatorFragment2 fragment = new ObjectAnimatorFragment2();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_object_animator, container, false);

        MyObjectAnimationView myObjectAnimationView = (MyObjectAnimationView) view.findViewById(R.id.myObjectAnimationView);

        ObjectAnimator anim = ObjectAnimator.ofObject(myObjectAnimationView, "color", new ColorEvaluator(),
                "#0000FF", "#FF0000");
        // 设置自定义View对象、背景颜色属性值 & 颜色估值器
        // 本质逻辑：
        // 步骤1：根据颜色估值器不断 改变 值
        // 步骤2：调用set（）设置背景颜色的属性值（实际上是通过画笔进行颜色设置）
        // 步骤3：调用invalidate()刷新视图，即调用onDraw（）重新绘制，从而实现动画效果

        anim.setDuration(8000);
        anim.start();

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
