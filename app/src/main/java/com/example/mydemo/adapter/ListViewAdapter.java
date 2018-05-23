package com.example.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/05/23
 * desc   :
 * version: 1.0.0
 */

public class ListViewAdapter extends BaseAdapter {


    private List<String> data;
    private LayoutInflater mLayoutInfalter;
    private Context context;

    public ListViewAdapter(Context context, List<String> data) {
        this.context = context;
        mLayoutInfalter = LayoutInflater.from(context);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mLayoutInfalter.inflate(android.R.layout.test_list_item, null);
            holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textView.setText(data.get(position));
        return convertView;
    }

    public class ViewHolder {
        public TextView textView;
    }
}
