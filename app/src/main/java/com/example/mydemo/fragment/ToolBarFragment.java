package com.example.mydemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Toast;

import com.example.mydemo.R;
import com.example.mydemo.view.MyToolBar;

/**
 * author : 宋佳
 * time   : 2018/05/21
 * desc   :
 * version: 1.0.0
 */

public class ToolBarFragment extends Fragment   {

    @LayoutRes
    int sampleLayoutRes;
    private MyToolBar myToolBar;

    public static ToolBarFragment newInstance(@LayoutRes int sampleLayoutRes) {
        ToolBarFragment fragment = new ToolBarFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);

        ViewStub sampleStub = (ViewStub) view.findViewById(R.id.sampleStub);
        sampleStub.setLayoutResource(sampleLayoutRes);
        sampleStub.inflate();



        myToolBar = (MyToolBar) view.findViewById(R.id.myToolBar);
        myToolBar.setmToolBarClick(new MyToolBar.toolbarClickListener() {
            @Override
            public void liftClick() {
                Toast.makeText(getActivity(),"左边啦",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(getActivity(),"右边啦",Toast.LENGTH_SHORT).show();
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
