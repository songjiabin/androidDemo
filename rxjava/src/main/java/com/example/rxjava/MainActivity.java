package com.example.rxjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.Subject;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 1) 创建 Observer  观察者  当被观察者发生变化的时候  将触发它

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        //同 观察者  Subscriber
        Subscriber<String> subscriber = new Subscriber<String>() {


            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };


        //被观察者
        Observable mObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onComplete();
            }
        });


        //观察者
        Observer mObserver = new Observer<Integer>() {
            //这是新加入的方法，在订阅后发送数据之前，
            //回首先调用这个方法，而Disposable可用于取消订阅
            @Override
            public void onSubscribe(Disposable d) {

                Log.i("宋佳宾", "onSubscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.i("宋佳宾", "onNext");
            }

            @Override
            public void onError(Throwable e) {
                Log.i("宋佳宾", "onError");
            }

            @Override
            public void onComplete() {
                Log.i("宋佳宾", "onComplete");
            }
        };


        mObservable.subscribe(mObserver);






        Observable.just("images/logo.png") // 输入类型 String
                .map(new Function<String, String>() {

                    @Override
                    public String apply(String s) throws Exception {
                        return null;
                    }
                }).subscribe(new Subject<String>() {
            @Override
            public boolean hasObservers() {
                return false;
            }

            @Override
            public boolean hasThrowable() {
                return false;
            }

            @Override
            public boolean hasComplete() {
                return false;
            }

            @Override
            public Throwable getThrowable() {
                return null;
            }

            @Override
            protected void subscribeActual(Observer<? super String> observer) {

            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });











    }
}
