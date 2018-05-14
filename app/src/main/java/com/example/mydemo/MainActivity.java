package com.example.mydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mydemo.view.MyProgressBar;

public class MainActivity extends AppCompatActivity {

    private MyProgressBar myProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myProgressBar = (MyProgressBar) findViewById(R.id.myProgressBar);
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myProgressBar.setSweepValue(90);
            }
        });
    }
}
