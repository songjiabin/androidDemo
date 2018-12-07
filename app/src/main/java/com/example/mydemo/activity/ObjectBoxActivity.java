package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mydemo.MyDemoApplication;
import com.example.mydemo.R;
import com.example.mydemo.bean.MyData2;
import com.example.mydemo.bean.MyData2_;

import java.util.List;

import io.objectbox.Box;

/**
 * author : 宋佳
 * time   : 2018/11/05
 * desc   :
 * version: 1.0.0
 */

public class ObjectBoxActivity extends AppCompatActivity {


    private Button mBtnPut;
    private Button mBtnGet;
    private Box<MyData2> mBox;
    private Button remove;
    private Button btn_query;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_box);
        mBox = MyDemoApplication.getBoxStore().boxFor(MyData2.class);
        initView();
        initListener();
    }

    private void initView() {
        mBtnPut = findViewById(R.id.btn_put);
        mBtnGet = findViewById(R.id.btn_get);
        remove = (Button) findViewById(R.id.remove);
        btn_query = (Button) findViewById(R.id.btn_query);
    }


    private void initListener() {
        mBtnPut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyData2 myData = new MyData2();
                myData.setGender_new_now("数据2222");
                myData.setUserAge(20);
                myData.setUserName("基本密码");
                mBox.put(myData);
            }
        });

        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<MyData2> myDataList = mBox.getAll();
                for (int i = 0; i < myDataList.size(); i++) {
                    MyData2 myData = myDataList.get(i);
                    Log.i("数据", myData.getGender_new_now());
                }
            }
        });
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除具体的id为 1的 数据
                mBox.remove(1);
                //删除 id 为1,2,3，的数据
                mBox.remove(1, 2, 3);
                //删除此表
                mBox.removeAll();
            }
        });
        btn_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查询 字段是具体的
                List<MyData2> data2List = mBox.query().equal(MyData2_.gender_new_now, "数据2222")
                        .build().find();


                List<MyData2> data2List2 = mBox.query().between(MyData2_.id, 20, 23).build().find();

                for (int i = 0; i < data2List2.size(); i++) {
                    Long id = data2List2.get(i).getId();
                    Log.i("数据", id + "");

                }
            }
        });
    }

}
