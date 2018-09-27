package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mydemo.R;
import com.example.mydemo.adapter.QQSkipAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/09/11
 * desc   :
 * version: 1.0.0
 */

public class QQSkidActivity extends AppCompatActivity {


    private RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_skip);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);


        QQSkipAdapter qqSkipAdapter = new QQSkipAdapter(this, getStringLists(), R.layout.item_layout_qq_skip);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setHasFixedSize(true);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setAdapter(qqSkipAdapter);
    }


    private List<String> getStringLists() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
//            list.add("第" + i + "条数据");
            list.add("");
        }
        return list;
    }

}
