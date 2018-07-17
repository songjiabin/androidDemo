package com.example.mydemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydemo.R;
import com.example.mydemo.view.MarqueeView;

/**
 * author : 宋佳
 * time   : 2018/07/17
 * desc   :
 * version: 1.0.0
 */

public class MarqueeFragment extends Fragment {


    @LayoutRes
    int sampleLayoutRes;
    private MarqueeView marqueeView;

    public static MarqueeFragment newInstance(@LayoutRes int sampleLayoutRes) {
        MarqueeFragment fragment = new MarqueeFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_marquee, container, false);

        marqueeView = (MarqueeView) view.findViewById(R.id.marqueeView);

        view.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始
                marqueeView.startSrcoller();
            }
        });

        view. findViewById(R.id.reStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新开始
                marqueeView.reStartScroll();
            }
        });

        view. findViewById(R.id.paruse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //暂停
                marqueeView.pauseScroll();
            }
        });
        view.findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止
                marqueeView.stopScroll();
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
