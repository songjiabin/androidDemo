package com.example.mydemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.example.mydemo.IMyAidlInterface;
import com.example.mydemo.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * author : 宋佳
 * time   : 2018/10/25
 * desc   :
 * version: 1.0.0
 */

public class AidlService extends Service {
    private List<Book> mBooks;

    public AidlService() {

    }


    //客户端与服务端绑定的时候回调
    @Override
    public IBinder onBind(Intent intent) {
        mBooks = new ArrayList<>();
        Book book=new Book("客户端");
        mBooks.add(book);
        Toast.makeText(getApplicationContext(),"绑定了",Toast.LENGTH_SHORT).show();
        return new MyBinder();
    }

    class MyBinder extends IMyAidlInterface.Stub {


        @Override
        public void addBook(Book book) throws RemoteException {
            mBooks.add(book);
            Log.i("《《《",book.getName());
        }

        @Override
        public List<Book> getBookList() throws RemoteException {

            return mBooks;
        }
    }
}
