package com.ralf.www.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private DownLoaderBinder mBinder = new DownLoaderBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        Log.e("ralf","onBind");
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("this is service")
                .setContentText("this is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);
        Log.e("ralf","oncreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("ralf","onStarCommand");
        Toast.makeText(this,"startcommand--- ",Toast.LENGTH_SHORT).show();
        //return START_NOT_STICKY;
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("ralf","onDestroy");
    }

    class DownLoaderBinder extends Binder{

        public void startDownLoad(){
            Log.e("ralf","Start download");
        }

        public int getProgress(){

            Log.e("ralf","Get Progress");
            return 0;
        }
    }
}
