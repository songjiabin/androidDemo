package com.example.mydemo.event;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mydemo.R;
import com.example.mydemo.activity.EventDemoActivity3;

/**
 * author : 宋佳
 * time   : 2018/07/13
 * desc   :
 * version: 1.0.0
 */

public class EventFragment extends Fragment {

    @LayoutRes
    int sampleLayoutRes;

    public static EventFragment newInstance(@LayoutRes int sampleLayoutRes) {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_event, container, false);

        view.findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //外部拦截法
                Intent intent = new Intent(getActivity(), EventDemoActivity.class);
                getActivity().startActivity(intent);
            }
        });
        view.findViewById(R.id.btn_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //内部拦截法
                Intent intent = new Intent(getActivity(), EventDemoActivity2.class);
                getActivity().startActivity(intent);
            }
        });

        view.findViewById(R.id.btn_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventDemoActivity3.class);
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
