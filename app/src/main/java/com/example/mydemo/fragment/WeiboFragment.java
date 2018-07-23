package com.example.mydemo.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydemo.R;
import com.example.mydemo.activity.WeiboActivity;

/**
 * author : 宋佳
 * time   : 2018/07/23
 * desc   :
 * version: 1.0.0
 */

public class WeiboFragment extends Fragment {


    @LayoutRes
    int sampleLayoutRes;

    public static WeiboFragment newInstance(@LayoutRes int sampleLayoutRes) {
        WeiboFragment fragment = new WeiboFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weibo, container, false);

        view.findViewById(R.id.textview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WeiboActivity.class);
                getActivity().startActivity(intent);
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
