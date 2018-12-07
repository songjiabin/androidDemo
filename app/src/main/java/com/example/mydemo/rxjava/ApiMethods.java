package com.example.mydemo.rxjava;

import com.example.mydemo.bean.Movie;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author : 宋佳
 * time   : 2018/11/02
 * desc   :
 * version: 1.0.0
 */

public class ApiMethods {


    public static void ApiSubscribe(Observable observable, Observer observer) {
        //上流
        observable.
                subscribeOn(Schedulers.io())//上流的线程
                .unsubscribeOn(Schedulers.io())//取消上流的线程
                .observeOn(AndroidSchedulers.mainThread())//下流的线程
                .subscribe(observer);
    }


    public static void getTopMovie(Observer<Movie> observer, int start, int count) {
        ApiSubscribe(Api.getApiService().getTopMovie(start, count), observer);
    }


    public static void getTopMovieReally(Observer<Movie> observer, int start, int count) {
        ApiSubscribe(ApiStrategy.getApiService().getTopMovie(start, count), observer);
    }


}
