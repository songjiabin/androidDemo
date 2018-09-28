package com.example.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.mydemo.R;

/**
 * author : 宋佳
 * time   : 2018/09/26
 * desc   :
 * version: 1.0.0
 */

public class MyViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);

        findViewById(R.id.btn_other_viewGroup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MyViewGroupActivity.this,OtherViewGroupActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_other_viewGroup2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MyViewGroupActivity.this,OtherViewGroupFourCornersActivity.class);
                startActivity(intent);
            }
        });

    }
}
