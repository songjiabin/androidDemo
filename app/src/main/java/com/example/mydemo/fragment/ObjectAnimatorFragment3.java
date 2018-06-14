package com.example.mydemo.fragment;

import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/06/14
 * desc   :
 * version: 1.0.0
 */

public class ObjectAnimatorFragment3 extends Fragment {

    @LayoutRes
    int sampleLayoutRes;
    private ViewWrapper wrapper;

    public static ObjectAnimatorFragment3 newInstance(@LayoutRes int sampleLayoutRes) {
        ObjectAnimatorFragment3 fragment = new ObjectAnimatorFragment3();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_object_animator3, container, false);

        Button btn = (Button) view.findViewById(R.id.btn);
        LinearLayout ll_object_animation = (LinearLayout) view.findViewById(R.id.ll_object_animation);
        wrapper = new ViewWrapper(btn);
        // 创建包装类,并传入动画作用的对象
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofInt(wrapper, "width", 500).setDuration(3000).start();
            }
        });



        //布局下面View的显示效果
        ScaleAnimation sa=new ScaleAnimation(0,1,0,1);
        sa.setDuration(2000);
        //设置动画的显示属性
        LayoutAnimationController lac=new LayoutAnimationController(sa,0.5F);
        lac.setOrder(LayoutAnimationController.ORDER_NORMAL);

        ll_object_animation.setLayoutAnimation(lac);





        return view;
    }


    // 提供ViewWrapper类,用于包装View对象
    // 本例:包装Button对象
    private static class ViewWrapper {
        private View mTarget;

        // 构造方法:传入需要包装的对象
        public ViewWrapper(View target) {
            mTarget = target;
        }

        // 为宽度设置get（） & set（）
        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }

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
