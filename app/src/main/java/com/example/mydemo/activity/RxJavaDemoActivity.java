package com.example.mydemo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.mydemo.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * author : 宋佳
 * time   : 2018/10/30
 * desc   :
 * version: 1.0.0
 */

public class RxJavaDemoActivity extends AppCompatActivity {


    private String TAG = "》》》》》》》》》》";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_rxjava);
//        k1();
//        k2();
//        k3();
//        k4();
//        k5();
//        k6();
//        k7();
//        k8();
//        k9();

//        k10();

        k11();
    }


    /**
     * 基础
     */
    private void k1() {
        //创建被观察者   上游事件
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);

                e.onNext(2);

                e.onNext(3);

                e.onComplete();

                e.onNext(4);
            }

        }).subscribe(new Observer<Integer>() {
            // 观察者  也是下游事件
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Integer value) {
                boolean isDisposed = disposable.isDisposed();
                if (isDisposed) {
                    Log.i(">>>", "》》》》》》》");
                } else {
                    Log.i(">>>", value + "");
                }

                if (value == 2) {
                    disposable.dispose();
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * Map
     * 它的作用是对发射时间发送的每一个事件应用一个函数，
     * 是的每一个事件都按照指定的函数去变化
     */
    private void k2() {


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) throws Exception {

                return integer + "宋佳宾";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(">>>", s);
            }
        });


    }


    /**
     * zip
     * 将两个发射器结合为一个发射器
     * zip 专用于合并事件，该合并不是连接（连接操作符后面会说），而是两两配对，也就意味着，最终配对出的 Observable 发射事件数目只和少的那个相同。
     * zip 组合事件的过程就是分别从发射器 A 和发射器 B 各取出一个事件来组合，并且一个事件只能被使用一次，组合的顺序是严格按照事件发送的顺序来进行的，
     */
    private void k3() {
        //上游事件 getStringObservable 和上游事件 getIntegerObservable 合并为一个事件
        Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("》》》", s);
            }
        });
    }


    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    //如果没有被终止事件
                    e.onNext("A");
                    Log.e(TAG, "String emit : A \n");
                    e.onNext("B");
                    Log.e(TAG, "String emit : B \n");
                    e.onNext("C");
                    Log.e(TAG, "String emit : C \n");

                }
            }
        });
    }

    private Observable<Integer> getIntegerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(1);

                    Log.e(TAG, "Integer emit : 1 \n");
                    e.onNext(2);

                    Log.e(TAG, "Integer emit : 2 \n");
                    e.onNext(3);

                    Log.e(TAG, "Integer emit : 3 \n");
                    e.onNext(4);

                    Log.e(TAG, "Integer emit : 4 \n");
                    e.onNext(5);

                    Log.e(TAG, "Integer emit : 5 \n");
                }
            }
        });
    }


    /**
     * concat
     * 将两个发射器结合成一个发射器。并不是合并。是一个发射器完成后。另外一个再反射。把两者的结果进行串联
     */
    private void k4() {
        Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i("》》》", integer + "");
                    }
                });
    }


    /**
     * FlatMap
     * 可以把一个发射器  Observable 通过某种方法转换为多个 Observables，然
     * 后再把这些分散的 Observables装进一个单一的发射器 Observable。
     * 但有个需要注意的是，flatMap 并不能保证事件的顺序，如果需要保证，
     * 需要用到我们下面要讲的 ConcatMap。
     */
    private void k5() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()) {
                    //如果没有拦截
                    e.onNext(1);
                    e.onNext(2);
                    e.onNext(3);
                }
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MICROSECONDS);
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e("》》》", "flatMap : accept : " + s + "\n");
                    }
                });
    }

    /**
     * 线程间的调度
     */
    private void k6() {
        //上流事件
        Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
            }
        });

        //下流接受事件
        Consumer consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.i(TAG, Thread.currentThread().getName());
                Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
                Log.d(TAG, "onNext: " + integer);
            }
        };

        observable.subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Log.i(TAG, Thread.currentThread().getName());
                        Log.i(TAG, o.toString());
                    }
                }).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i(TAG, o.toString());
                Log.i(TAG, Thread.currentThread().getName());
            }
        }).subscribe(consumer);


//        observable.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(consumer);

    }


    /**
     * 线程间的调度2
     */
    private void k7() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, Thread.currentThread().getName());
                e.onNext(1);
            }
        }).subscribeOn(Schedulers.newThread()).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, integer + "");
                        Log.e(TAG, Thread.currentThread().getName());
                    }
                });
    }


    private void k8() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Exception {
                return integer + "";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, s);
            }
        });


        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onNext(5);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i(TAG, s);
            }
        });


    }


    private void k9() {
        Observable observable_a = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);

            }
        }).subscribeOn(Schedulers.io());
        Observable observable_b = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("a");
                e.onNext("b");
                e.onComplete();
                e.onNext("c");
            }
        }).subscribeOn(Schedulers.io());


        Observable.zip(observable_a, observable_b, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer i, String s) throws Exception {
                return i + s;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("数据", o.toString());
            }
        });
    }


    private void k10() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; ; i++) {
                    e.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io())
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) throws Exception {
                        return integer % 10 == 0;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())//下游在主线程中
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d(TAG, "" + integer);
                    }
                });
    }

    private Subscription mSubscription;

    private void k11() {
        //上游事件
        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emit 1");
                emitter.onNext(1);
                Log.d(TAG, "emit 2");
                emitter.onNext(2);
                Log.d(TAG, "emit 3");
                emitter.onNext(3);
                Log.d(TAG, "emit complete");
                emitter.onComplete();


            }
        }, BackpressureStrategy.ERROR)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe");
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, integer + "");
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.w(TAG, "onError: ", t);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //接收事件
                mSubscription.request(1);
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止事件
                mSubscription.cancel();
            }
        });
    }


}
