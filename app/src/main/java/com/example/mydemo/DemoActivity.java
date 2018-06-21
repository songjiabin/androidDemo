package com.example.mydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.searchview.ICallBack;
import com.example.searchview.SearchView;

/**
 * author : 宋佳
 * time   : 2018/05/22
 * desc   :
 * version: 1.0.0
 */

public class DemoActivity extends AppCompatActivity {


    private SearchView searchView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search_view);


        searchView = (SearchView) findViewById(R.id.searchView);


        searchView.setiCallBack(new ICallBack() {
            @Override
            public void searchAciton(String string) {
                Toast.makeText(DemoActivity.this, "搜索", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void backAciton() {
                Toast.makeText(DemoActivity.this, "返回", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
