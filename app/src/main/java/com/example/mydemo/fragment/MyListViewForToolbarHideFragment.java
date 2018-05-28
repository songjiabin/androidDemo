package com.example.mydemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.example.mydemo.R;
import com.example.mydemo.adapter.ListViewAdapter;
import com.example.mydemo.view.MyListViewForToolbarHide;

import java.util.ArrayList;
import java.util.List;

/**
 * time   : 2018/05/23
 * desc   : listiview 滚动到上面后 隐藏 toolBar
 * version: 1.0.0
 */

public class MyListViewForToolbarHideFragment extends Fragment {

    @LayoutRes
    int sampleLayoutRes;

    public static MyListViewForToolbarHideFragment newInstance(@LayoutRes int sampleLayoutRes) {
        MyListViewForToolbarHideFragment fragment = new MyListViewForToolbarHideFragment();
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

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);


        MyListViewForToolbarHide myListView = (MyListViewForToolbarHide) view.findViewById(R.id.myListView);

        ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(), getListString());
        myListView.setToolbar(toolbar);
        myListView.setAdapter(listViewAdapter);


        return view;
    }

    public List<String> getListString() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i + "");
        }
        return list;
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
