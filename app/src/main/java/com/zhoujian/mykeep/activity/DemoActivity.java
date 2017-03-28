package com.zhoujian.mykeep.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import com.zhoujian.mykeep.R;
import java.lang.ref.WeakReference;

/**
 * Created by zhoujian on 2017/3/18.
 */

public class DemoActivity extends AppCompatActivity
{

    private MyHandler mHandler = new MyHandler(this);
    private static class MyHandler extends Handler {
        private WeakReference<Context> reference;
        public MyHandler(Context context) {
            reference = new WeakReference<>(context);
        }
        @Override
        public void handleMessage(Message msg) {
            MainActivity activity = (MainActivity) reference.get();
            if(activity != null)
            {
                //...更新UI操作

            }
        }
    }

    public  static Person mPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
//https://github.com/zeke123/MyKeep.git
        if(mPerson == null){
            mPerson = new Person();
        }

    }

    class Person
    {

        private String name;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }
    }


    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            try
            {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private void initDatas()
    {
        //...子线程获取数据，在主线程中更新UI
        Message message = Message.obtain();
        mHandler.sendMessage(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除消息队列中所有消息和所有的Runnable
        mHandler.removeCallbacksAndMessages(null);
    }
}




