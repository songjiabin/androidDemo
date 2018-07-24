package com.example.mydemo.fragment;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/07/24
 * desc   :
 * version: 1.0.0
 */

public class WeiBoTabRightFragment extends Fragment {

    @LayoutRes
    int sampleLayoutRes;

    public static WeiBoTabRightFragment newInstance(@LayoutRes int sampleLayoutRes) {
        WeiBoTabRightFragment fragment = new WeiBoTabRightFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_shape_demo, container, false);


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
