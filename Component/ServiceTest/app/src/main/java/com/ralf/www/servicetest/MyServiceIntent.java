package com.ralf.www.servicetest;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 作者：Ralf on 2017/11/7 21:25
 * desc：wang_lxin@163.com
 */

public class MyServiceIntent extends IntentService {

    public MyServiceIntent(){
     super("MyServiceIntent");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.e("ralf","MyIntentService thread id is " + Thread.currentThread().getId());
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.e("ralf","MyIntentService is destroyed!");
    }
}
