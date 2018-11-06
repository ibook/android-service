package cn.netkiller.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private MyBinder myBinder = new MyBinder();

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Service", "onCreate() executed");
        Log.d("Service", "MyService thread id is " + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d("Service", "onStartCommand() begin");
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 开始执行后台任务
                Log.d("Service", "onStartCommand() executed");
            }
        }).start();

        Log.d("Service", "onStartCommand() end");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Service", "onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    class MyBinder extends Binder {

        public void startTask() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 执行具体的任务
                    Log.d("Service", "startTask()");
                }
            }).start();
        }

    }
}
