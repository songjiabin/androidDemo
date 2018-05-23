package com.example.mydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.mydemo.adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/05/22
 * desc   :
 * version: 1.0.0
 */

public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_my_listview);
        ListView myListView = (ListView)  findViewById(R.id.myListView);

        ListViewAdapter listViewAdapter = new ListViewAdapter(this, getListString());
        myListView.setAdapter(listViewAdapter);
    }


    public List<String> getListString() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
        return list;
    }
}
