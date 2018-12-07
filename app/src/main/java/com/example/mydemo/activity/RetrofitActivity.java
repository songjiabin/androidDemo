package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.mydemo.R;
import com.example.mydemo.bean.Movie;
import com.example.mydemo.bean.Subjects;
import com.example.mydemo.rxjava.ApiMethods;
import com.example.mydemo.rxjava.ApiService;
import com.example.mydemo.rxjava.MyObserver;
import com.example.mydemo.rxjava.ObserverOnNextListener;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author : 宋佳
 * time   : 2018/11/02
 * desc   :
 * version: 1.0.0
 */

public class RetrofitActivity extends AppCompatActivity {


    private static final String TAG = ">>>>";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                k1();
//                k2();
//                k3();
                k4();
            }
        });
    }


    private void k1() {
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//将请求的结果转为实体
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //适配RxJava2.0, RxJava1.x 则为RxJavaCallAdapterFactory.create()
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Movie>() {
                    @Override
                    public void accept(Movie movie) throws Exception {
                        Log.d(TAG, "onNext: " + movie.getTitle());
                        List<Subjects> list = movie.getSubjects();
                        for (Subjects sub : list) {
                            Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "onError: " + throwable.getMessage());

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d(TAG, "onComplete: Over!");
                    }
                });
    }

    private void k2() {
        ApiMethods.getTopMovie(new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Movie movie) {
                Log.d(TAG, "onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e(TAG, "onError: " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: Over!");
            }
        }, 0, 10);
    }

    private void k3() {
        ApiMethods.getTopMovie(new MyObserver<Movie>(this, new ObserverOnNextListener<Movie>() {
            @Override
            public void onNext(Movie movie) {
                Log.d(TAG, "onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }
        }), 0, 10);
    }


    private void k4() {
        ApiMethods.getTopMovieReally(new MyObserver<Movie>(this, new ObserverOnNextListener<Movie>() {
            @Override
            public void onNext(Movie movie) {
                Log.d(TAG, "onNext: " + movie.getTitle());
                List<Subjects> list = movie.getSubjects();
                for (Subjects sub : list) {
                    Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                }
            }
        }), 0, 10);
    }


}
