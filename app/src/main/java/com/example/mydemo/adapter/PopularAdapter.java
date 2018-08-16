package com.example.mydemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mydemo.R;
import com.example.mydemo.bean.PopularWindowBean;
import com.example.mydemo.view.PopularItemLayout;

import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/08/16
 * desc   :
 * version: 1.0.0
 */

public class PopularAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<PopularWindowBean> data;

    public PopularAdapter(Context context, List<PopularWindowBean> data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_popular_nomal, null);
            viewHolder.popularItemLayout = (PopularItemLayout) convertView.findViewById(R.id.rl_popular_parent);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.itemCaption);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PopularWindowBean bean = data.get(position);
        viewHolder.tv.setText(bean.getData());
        final ListView lv = (ListView) parent;
        viewHolder.popularItemLayout.setChecked(lv.isItemChecked(position));
        return convertView;
    }

    private class ViewHolder {
        private TextView tv;
        private PopularItemLayout popularItemLayout;
    }
}
