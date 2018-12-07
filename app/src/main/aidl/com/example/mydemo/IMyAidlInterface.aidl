// IMyAidlInterface.aidl
package com.example.mydemo;

// Declare any non-default types here with import statements
import com.example.mydemo.bean.Book;

interface IMyAidlInterface {


     void addBook(in Book book);

     List<Book> getBookList();


}
