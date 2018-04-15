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

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private MyService.DownLoaderBinder mBinder = null;
    private boolean isConnected = false;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (MyService.DownLoaderBinder) service;

            mBinder.startDownLoad();
            mBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

        Log.e("ralf","onCreate--");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.e("ralf","onStart--");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        Log.e("ralf","activity 2 is destroyed !");
        if (isConnected){
            unbindService(mConnection);
        }
    }

    @Override
    protected void onRestart(){

        super.onRestart();
        Log.e("ralf","onRestart()");

    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.e("ralf","onResume--");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.e("ralf","onPause--");

    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.e("ralf","onStop--");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button1:
                Intent intentStart = new Intent(this, MyService.class);
                startService(intentStart);
                break;

            case R.id.button2:
                Intent intentStop = new Intent(this, MyService.class);
                stopService(intentStop);
                break;

            case R.id.button3:

                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, mConnection, BIND_AUTO_CREATE);
                isConnected = true;
                break;

            case R.id.button4:
                if (isConnected){
                    unbindService(mConnection);
                    isConnected = false;
                }
                break;

            default:
                break;
        }
    }
}
