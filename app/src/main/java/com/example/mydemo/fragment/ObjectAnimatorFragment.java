package com.example.mydemo.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/06/14
 * desc   :
 * version: 1.0.0
 */

public class ObjectAnimatorFragment extends Fragment {

    @LayoutRes
    int sampleLayoutRes;

    public static ObjectAnimatorFragment newInstance(@LayoutRes int sampleLayoutRes) {
        ObjectAnimatorFragment fragment = new ObjectAnimatorFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_value_animator, container, false);

        Button btn = (Button) view.findViewById(R.id.btn);
        Button btn2 = (Button) view.findViewById(R.id.btn2);
        Button btn3 = (Button) view.findViewById(R.id.btn3);
        Button btn4 = (Button) view.findViewById(R.id.btn4);
        Button btn5 = (Button) view.findViewById(R.id.btn5);
        final Button btn6 = (Button) view.findViewById(R.id.btn6);

        //透明
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn, "alpha", 1f, 0f, 1f);
        //操作button的透明度
        objectAnimator.setDuration(3000);
        objectAnimator.start();

        //旋转
        // 动画效果是:0 - 360
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(btn2, "rotation", 0f, 360f);
        objectAnimator2.setDuration(5000);
        objectAnimator2.start();


        //平移
        final float curTranslationX = btn3.getX();
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(btn3, "translationX", curTranslationX, curTranslationX + 300, curTranslationX);
        objectAnimator3.setDuration(5000);
        objectAnimator3.start();

        //缩放
        ObjectAnimator animator = ObjectAnimator.ofFloat(btn4, "scaleX", 1f, 3f, 1f);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴缩放
        // 动画效果是:放大到3倍,再缩小到初始大小
        animator.setDuration(5000);
        animator.start();




        //组合动画
//        AnimatorSet.play(Animator anim)   ：播放当前动画
//        AnimatorSet.after(long delay)   ：将现有动画延迟x毫秒后执行
//        AnimatorSet.with(Animator anim)   ：将现有动画和传入的动画同时执行
//        AnimatorSet.after(Animator anim)   ：将现有动画插入到传入的动画之后执行
//        AnimatorSet.before(Animator anim) ：  将现有动画插入到传入的动画之前执行


        //主要动画是平移，平移过程中伴随旋转动画，平移完后进行透明度变化
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //组合动画
                // 步骤1：设置需要组合的动画效果
                ObjectAnimator translation = ObjectAnimator.ofFloat(btn6, "translationX", curTranslationX, 300, curTranslationX);
                // 平移动画
                ObjectAnimator rotate = ObjectAnimator.ofFloat(btn6, "rotation", 0f, 360f);
                // 旋转动画
                ObjectAnimator alpha = ObjectAnimator.ofFloat(btn6, "alpha", 1f, 0f, 1f);
                // 透明度动画

                // 步骤2：创建组合动画的对象
                AnimatorSet animSet = new AnimatorSet();

                // 步骤3：根据需求组合动画
                animSet.play(translation).with(rotate).before(alpha);
                animSet.setDuration(5000);

                // 步骤4：启动动画
                animSet.start();
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
