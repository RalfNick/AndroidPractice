package com.ralf.www.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService.DownLoaderBinder mBinder = null;
    private boolean isConnected = false;

    //创建ServiceConnection实例
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mBinder = (MyService.DownLoaderBinder) iBinder;
            //调用MyService.DownLoaderBinder中的方法
            mBinder.startDownLoad();
            mBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 开启service和关闭service
        Button buttonStart = (Button) findViewById(R.id.button_start);
        Button buttonStop = (Button) findViewById(R.id.button_stop);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

        //bind service 和unbind service
        Button buttonBindService = (Button) findViewById(R.id.bind_button);
        Button buttonUnBindService = (Button) findViewById(R.id.unbind_button);

        buttonBindService.setOnClickListener(this);
        buttonUnBindService.setOnClickListener(this);

        //start the intentservice
        Button buttonStartIntentService = (Button) findViewById(R.id.button_startserviceintent);
        buttonStartIntentService.setOnClickListener(this);

        //start another activity
        Button buttonStartActivity2 = (Button) findViewById(R.id.button_activity2);
        buttonStartActivity2.setOnClickListener(this);

        Log.e("ralf","main---onCreate()");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_start:
                Intent intentStart = new Intent(this, MyService.class);
                startService(intentStart);
                break;

            case R.id.button_stop:
                Intent intentStop = new Intent(this, MyService.class);
                stopService(intentStop);
                break;

            case R.id.bind_button:

                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, mConnection, BIND_AUTO_CREATE);
                isConnected = true;
                break;

            case R.id.unbind_button:
                if (isConnected){
                    unbindService(mConnection);
                    isConnected = false;
                }
                break;

            case R.id.button_startserviceintent:
                Log.e("ralf", "main thread is " + Thread.currentThread());

                Intent intentService = new Intent(this, MyServiceIntent.class);
                startService(intentService);
                break;

            case R.id.button_activity2:
                Intent intentActivity2 = new Intent(this, Main2Activity.class);
                startActivity(intentActivity2);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.e("ralf","main---onStart()");
    }

    @Override
    protected void onRestart(){

        super.onRestart();
        Log.e("ralf","main---onRestart()");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.e("ralf","main---onResume--");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.e("ralf","main---onPause--");

    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.e("ralf","main---onStop--");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.e("ralf","main activity is destroyed!");

        if (isConnected){
            unbindService(mConnection);
        }
    }
}
