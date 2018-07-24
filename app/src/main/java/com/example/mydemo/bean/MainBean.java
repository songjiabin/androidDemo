package com.example.mydemo.bean;

/**
 * author : 宋佳
 * time   : 2018/07/24
 * desc   :
 * version: 1.0.0
 */

public class MainBean {

    public String img;
    public String title;
    public String content;
    public String time;

    @Override
    public String toString() {
        return "MainBean{" +
                "img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
