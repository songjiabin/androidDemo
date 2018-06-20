package com.example.mydemo;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mydemo.db.RecordSQLiteOpenHelper;

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
        setContentView(R.layout.layout_search);

        // 步骤1：创建DatabaseHelper对象
        // 注：此时还未创建数据库
        SQLiteOpenHelper dbHelper = new RecordSQLiteOpenHelper(DemoActivity.this, "test_mydemo");

        // 步骤2：真正创建 / 打开数据库
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase(); // 创建 or 打开 可读/写的数据库
//        SQLiteDatabase sqliteDatabase = dbHelper.getReadableDatabase(); // 创建 or 打开 可读的数据库






    }

}
