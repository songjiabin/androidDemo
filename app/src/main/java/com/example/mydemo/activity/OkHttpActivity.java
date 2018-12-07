package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author : 宋佳
 * time   : 2018/09/29
 * desc   :
 * version: 1.0.0
 */

public class OkHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //同步方法
        execute();

        //异步
        enqueue();
    }

    /**
     * 同步方法
     */
    private void execute() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).build();
                Request request = new Request.Builder().url("http://www.baidu.com").get().build();

                //连接 resquest  response 的桥梁
                Call call = okHttpClient.newCall(request);

                try {
                    Response response = call.execute();
                    String okhttpString = response.body().toString();
                    Log.i("》》》", okhttpString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 异步方法
     */
    private void enqueue() {



        OkHttpClient okHttpClient = new OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).build();

        Request request = new Request.Builder().url("http://www.baidu.com").get().build();


        Call call = okHttpClient.newCall(request);

        //当调用enqueue的时候回开启一个新的工作线程
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }
}
