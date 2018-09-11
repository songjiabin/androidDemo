package com.example.mydemo.adapter;

import android.content.Context;

import com.example.mydemo.R;
import com.example.mydemo.superadapter.SuperAdapter;
import com.example.mydemo.superadapter.SuperViewHolder;

import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/09/11
 * desc   :
 * version: 1.0.0
 */

public class QQSkipAdapter extends SuperAdapter<String> {


    public QQSkipAdapter(Context context, List<String> items, int layoutResId) {
        super(context, items, layoutResId);
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, String item) {
        holder.setText(R.id.tv_content, item);
    }
}
