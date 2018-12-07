package com.example.mydemo.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayDeque;

/**
 * author : 宋佳
 * time   : 2018/10/29
 * desc   :
 * version: 1.0.0
 */

/**
 * 三个参数
 * 1、 传入参数类型，即doInBackground()方法中的参数类型;
 * 2、异步任务执行过程中返回的下载进度类型，即publishProgress()和onProgressUpdate()方法中传入的参数类型；
 * 3、Result: 异步任务执行完返回的结果类型，即doInBackground()方法中返回值的类型。
 */
public class AsyncTaskDemo extends AsyncTask<Void, Integer, Integer> {

    private Context context;

    public AsyncTaskDemo(Context context) {
        this.context = context;
        ArrayDeque<String> arrayDeque=new ArrayDeque<>();
        arrayDeque.add("1");
        arrayDeque.add("2");
        arrayDeque.add("3");

    }


    /**
     * 运行在ui线程中。在调用doInBackground()之前调用
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /**
     * 运行在非ui线程。可以执行耗时操作
     *
     * @param voids
     * @return
     */
    @Override
    protected Integer doInBackground(Void... voids) {
        int i = 0;
        while (i < 10) {
            i++;
            publishProgress(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 运行在ui线程中。 在doInBackground()执行完毕后调用
     *
     * @param o
     */
    @Override
    protected void onPostExecute(Integer o) {
        super.onPostExecute(o);
        Toast.makeText(context, "执行完毕", Toast.LENGTH_SHORT).show();
    }

    /**
     * 在  doInBackgroun方法调用后执行。
     *
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Log.i("进度", values.toString());
    }
}
