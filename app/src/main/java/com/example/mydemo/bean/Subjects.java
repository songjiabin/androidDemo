package com.example.mydemo.bean;

/**
 * author : 宋佳
 * time   : 2018/11/02
 * desc   :
 * version: 1.0.0
 */

public class Subjects {
    private String title, year, id;

    public Subjects(String title, String year, String id) {
        this.title = title;
        this.year = year;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getId() {
        return id;
    }

}
