package com.yey.study;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.yey.ok.Book;
import com.yey.ok.IBookManager;
import com.yey.study.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private final String TAG = this.getClass().getName();
    private boolean isBind;
    // Binder代理对象或者Binder本地对象
    private IBookManager iBookManager;

    // 服务链接
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // service 它是Binder引用对象
            // 如果同进程返回Binder本地对象
            // 如果不同进程返回Binder代理对象
            iBookManager = IBookManager.Stub.asInterface(service);
            try {
                // 设置死亡代理
                service.linkToDeath(mDeathRecipient, 0);
                printBookList();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    // 定义一个死亡接收者, 当服务进程挂了这里会收到通知
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (iBookManager == null) return;
            // 取消设置的代理
            // asBinder()如果是同进程就返回Binder本地对象
            // 如果是不同进程就返回Binder引用对象.
            iBookManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            iBookManager = null;
            Log.e(TAG, "收到死亡通知,已经接触绑定");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initListener();
    }

    private void initListener() {
        // 开启远程服务
        mainBinding.button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service = new Intent("com.yey.app_server.bookmanagerserver");
                service.setPackage("com.yey.app_server");
                startService(service);
            }
        });
        // 停止远程服务
        mainBinding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service = new Intent("com.yey.app_server.bookmanagerserver");
                service.setPackage("com.yey.app_server");
                stopService(service);
            }
        });
        // 绑定远程服务
        mainBinding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service = new Intent("com.yey.app_server.bookmanagerserver");
                service.setPackage("com.yey.app_server");
                bindService(service, connection, BIND_AUTO_CREATE);
                isBind = true;
            }
        });
        // 解绑远程服务
        mainBinding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBind) {
                    unbindService(connection);
                    isBind = false;
                }
            }
        });
        // 添加书籍
        mainBinding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    iBookManager.addBook(new Book(2, "鬼吹灯"));
                    iBookManager.addBook(new Book(3, "盗墓笔记"));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        // 打印书籍列表
        mainBinding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    printBookList();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        //解绑
        if (isBind) {
            unbindService(connection);
            isBind = false;
        }
        super.onDestroy();
    }

    /**
     * 打印服务端数据
     *
     * @throws RemoteException
     */
    private void printBookList() throws RemoteException {
        List<Book> bookList = iBookManager.getBookList();
        for (Book mBook : bookList) {
            Log.e(TAG, "本书名字 " + mBook.getmName() + " 本书ID " + mBook.getmBookId());
        }
    }
}
