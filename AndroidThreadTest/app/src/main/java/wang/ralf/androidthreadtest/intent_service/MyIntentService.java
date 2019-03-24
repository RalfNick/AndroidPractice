package wang.ralf.androidthreadtest.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author Ralf
 * DESCRIPTION
 * @name MyIntentService
 * @email -
 * @date 2019/03/18 下午3:49
 **/
public class MyIntentService extends IntentService {

    private static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super(TAG);
    }

    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e(TAG, "onStart");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e(TAG, "onHandleIntent");
        String tag = intent != null ? intent.getStringExtra("tag") : "";
        switch (tag) {
            case "task1":
                try {
                    Thread.sleep(3000);
                    Log.e(TAG, "task1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case "task2":
                try {
                    Thread.sleep(3000);
                    Log.e(TAG, "task2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }
}
