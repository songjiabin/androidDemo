package com.example.mydemo.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/07/24
 * desc   :
 * version: 1.0.0
 */

public class WeiBoTabLeftFragment extends Fragment {

    @LayoutRes
    int sampleLayoutRes;

    public static WeiBoTabLeftFragment newInstance(@LayoutRes int sampleLayoutRes) {
        WeiBoTabLeftFragment fragment = new WeiBoTabLeftFragment();
        Bundle args = new Bundle();
        args.putInt("sampleLayoutRes", sampleLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weibo_left, container, false);

        ListView listview = (ListView) view.findViewById(R.id.listview);
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            stringList.add(i + "数据");
        }


        MyAdapter myAdapter = new MyAdapter(stringList);
        listview.setAdapter(myAdapter);


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


    public class MyAdapter extends BaseAdapter {

        private List<String> data;

        public MyAdapter(List<String> data) {
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, android.view.View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (viewHolder == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getActivity()).inflate(android.R.layout.test_list_item, null);
                viewHolder.textView = (TextView) convertView.findViewById(android.R.id.text1);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.textView.setText(data.get(position));

            return convertView;
        }


    }

    private class ViewHolder {
        private TextView textView;


    }


}
