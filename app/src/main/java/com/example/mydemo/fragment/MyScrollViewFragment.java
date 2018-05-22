package com.example.mydemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/05/22
 * desc   :
 * version: 1.0.0
 */

public class MyScrollViewFragment extends Fragment {

    @LayoutRes
    int sampleLayoutRes;


    public static MyScrollViewFragment newInstance(@LayoutRes int sampleLayoutRes) {
        MyScrollViewFragment fragment = new MyScrollViewFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_my_scrollview, container, false);






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
