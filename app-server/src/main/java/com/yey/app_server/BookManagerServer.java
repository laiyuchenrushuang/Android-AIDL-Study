package com.yey.app_server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.yey.ok.Book;
import com.yey.ok.IBookManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BookManagerServer extends Service {
    // CopyOnWriteArrayList写是同步的, 支持并发读取.
    public CopyOnWriteArrayList mBookList = new CopyOnWriteArrayList();

    @Override
    public void onCreate() {
        super.onCreate();
        mBookList.add(new Book(0,"Android开发艺术探索"));
        mBookList.add(new Book(1,"Android内核剖析"));
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private Binder mBinder = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            mBookList.add(book);
        }
    };
}
