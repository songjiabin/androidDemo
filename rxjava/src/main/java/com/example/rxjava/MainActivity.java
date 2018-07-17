package com.example.rxjava;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.rxjava.bean.A;
import com.example.rxjava.bean.B;
import com.example.rxjava.bean.C;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.observables.GroupedObservable;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {


    private static final String TAG = "MainActivity";
    private Subscription observable1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(intent);
            }
        });


        Observable.just("Hello world").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s + "I am kyrie!";
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Log.e("宋佳宾", "call: " + s);
                    }
                });


        Observable.just("I am songjiabin").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("宋佳宾", s);
            }
        });


        //被观察者创建
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("1");
                subscriber.onNext("2");
                subscriber.onCompleted();
                subscriber.onNext("3");
            }
        });


        //接受者
        Observer observer = new Observer() {
            @Override
            public void onCompleted() {
                Log.e("宋佳宾", "完成");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.e("宋佳宾", o.toString());
            }
        };

        observable.subscribe(observer);

        //也是接受者
        Subscriber subscriber = new Subscriber() {

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Log.e("宋佳宾", o.toString() + "---------->");
            }
        };
        observable.subscribe(subscriber);


        Observable.just("ni", "you").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("宋佳宾", s);
            }
        });


        List<String> list = new ArrayList<>();
        list.add("我们");
        list.add("we are");

        // from 发送是 list中的每个 item
        Observable.from(list).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object s) {
                Log.e("宋佳宾", s.toString());
            }
        });


        List<String> list2 = new ArrayList<>();
        list2.add("你们");
        list2.add("you are");
        Observable.just(list2).subscribe(new Subscriber<List<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<String> list) {
                for (int i = 0; i < list.size(); i++) {
                    Log.e("宋佳宾", list.get(i));
                }
            }
        });

        //  创建一个按固定时间间隔发射整数序列的Observable，可用作定时器。
        observable1 = Observable.interval(1, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                Log.e("宋佳宾", aLong + "");
                if (aLong == 10) {
                    observable1.unsubscribe();
                }
            }
        });


        //有观察者订阅时才创建Observable，    并且为每个观察者创建一个新的Observable
        Observable<String> deferObservable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("deferObservable");
            }
        });
        deferObservable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("宋佳宾", s);
            }
        });

        // 使用range( ),创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常。
        Observable.range(0, 5).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e("宋佳宾", integer + "");
                //打印结结果是 0 ，1 ，2 ，3 ，4
            }
        });


        //使用timer( ),创建一个Observable，它在一个给定的延迟后发射一个特殊的值。   等同于Android中Handler的postDelay( )方法。
        Observable timeObservable = Observable.timer(10, TimeUnit.SECONDS);   //3秒后发射一个值
        timeObservable.just("adjfa", "dlafj", "sdee").subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("宋佳宾", s);
            }
        });

        // 使用repeat( ),创建一个重复发射特定数据的Observable。
        Observable.just("22222", "dddddd").repeat(3).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("宋佳宾", s);
            }
        });

        //使用 action1进行简化
        Observable.just("1", 1, "sbd").subscribe(new Action1<Object>() {
            @Override
            public void call(Object object) {
                Log.e("宋佳宾", object.toString());
            }
        });


        //使用 flatMap
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Student student = new Student();
            List<Student.Course> courses = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                Student.Course course = new Student.Course("课程" + j);
                courses.add(course);
            }
            student.setList(courses);
            students.add(student);
        }


        Observable.from(students).flatMap(new Func1<Student, Observable<Student.Course>>() {
            @Override
            public Observable<Student.Course> call(Student student) {
                return Observable.from(student.getList());
            }
        }).subscribe(new Action1<Student.Course>() {
            @Override
            public void call(Student.Course course) {
                Log.e("宋佳宾", course.name);
            }
        });

        List<Object> listObject = new ArrayList<>();
        A a = new A();
        B b = new B();
        C c = new C();

        listObject.add(a);
        listObject.add(b);
        listObject.add(c);


        Observable from = Observable.just(listObject);
        from.groupBy(new Func1<Object, Integer>() {
            @Override
            public Integer call(Object o) {


                if (o instanceof A) {
                    return 1;
                } else if (o instanceof B) {
                    return 2;
                } else if (o instanceof C) {
                    return 3;
                }else {
                    return 4;
                }




            }
        }).subscribe(new Action1<GroupedObservable<Integer, Object>>() {
            @Override
            public void call(GroupedObservable<Integer, Object> objectIntegerGroupedObservable) {
                int sign = objectIntegerGroupedObservable.getKey();


                switch (sign) {
                    case 1:
                        objectIntegerGroupedObservable.subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object a) {
                                Log.d("宋佳宾", "class A - " + a.getClass().getName());
                            }
                        });
                        break;
                    case 2:
                        objectIntegerGroupedObservable.subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object a) {
                                Log.d("宋佳宾", "class B - " + a.getClass().getName());
                            }
                        });
                        break;
                    case 3:
                        objectIntegerGroupedObservable.subscribe(new Action1<Object>() {
                            @Override
                            public void call(Object a) {
                                Log.d("宋佳宾", "class C - " + a.getClass().getName());
                            }
                        });
                        break;
                    default:
                        Log.d("宋佳宾", "other class");
                        break;
                }

            }
        });


    }

    static class Student {

        private List<Course> list;


        public List<Course> getList() {
            return list;
        }

        public void setList(List<Course> list) {
            this.list = list;
        }

        static class Course {
            private String name;

            public Course(String name) {
                this.name = name;
            }
        }


    }


}
