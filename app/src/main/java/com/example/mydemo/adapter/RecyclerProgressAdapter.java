package com.example.mydemo.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.SeekBar;

import com.example.mydemo.R;
import com.example.mydemo.bean.SuperAdapterBean;
import com.example.mydemo.superadapter.SuperAdapter;
import com.example.mydemo.superadapter.SuperViewHolder;

import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/08/17
 * desc   :
 * version: 1.0.0
 */

public class RecyclerProgressAdapter extends SuperAdapter<SuperAdapterBean> {


    private List<SuperAdapterBean> items;

    public RecyclerProgressAdapter(Context context, List<SuperAdapterBean> items, int layoutResId) {
        super(context, items, layoutResId);
        this.items = items;
    }

    @Override
    public void onBind(SuperViewHolder holder, int viewType, int layoutPosition, final SuperAdapterBean item) {



        final SeekBar seekBar = (SeekBar) holder.findViewById(R.id.seekBar);


        seekBar.post(new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(item.getProgress());
            }
        });





        seekBar.setTag(layoutPosition);
        seekBar.setOnSeekBarChangeListener(new onSeekListener());

    }


    public class onSeekListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            //当改变的时候
            Log.i(">>>", progress + "");
            int tag = (int) seekBar.getTag();
            items.get(tag).setProgress(progress);


        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }


}
