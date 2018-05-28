package com.example.mydemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.utils.ScreenUtils;
import com.example.mydemo.view.MyScroller2;

/**
 * time   : 2018/05/28
 * desc   :  Scrollerto  ScrollBy
 * version: 1.0.0
 */

public class MyScrollerFragment extends Fragment {

    @LayoutRes
    int sampleLayoutRes;
    private int moveX = 0;

    public static MyScrollerFragment newInstance(@LayoutRes int sampleLayoutRes) {
        MyScrollerFragment fragment = new MyScrollerFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.layout_my_scroller2, container, false);


        TextView left = (TextView) view.findViewById(R.id.left);
        TextView right = (TextView) view.findViewById(R.id.right);
        final MyScroller2 myScroller2 = (MyScroller2) view.findViewById(R.id.myScroller2);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (moveX < 0) {
                    moveX = 0;
                }
                if (moveX >= ScreenUtils.getScreenWidth(getActivity()) * 3) {
                    moveX = ScreenUtils.getScreenWidth(getActivity()) * 3;
                }
                moveX -= ScreenUtils.getScreenWidth(getActivity());
                myScroller2.scrollTo(moveX, 0);
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (moveX < 0) {
                    moveX = 0;
                }
                if (moveX >= ScreenUtils.getScreenWidth(getActivity()) * 3) {
                    moveX = ScreenUtils.getScreenWidth(getActivity()) * 3;
                }
                moveX += ScreenUtils.getScreenWidth(getActivity());
                myScroller2.scrollTo(moveX, 0);

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
