package wang.ralf.androidthreadtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import wang.ralf.androidthreadtest.async_task.AbstractTask;
import wang.ralf.androidthreadtest.async_task.MyTask;
import wang.ralf.androidthreadtest.handler_thread.MyHandlerThread;
import wang.ralf.androidthreadtest.intent_service.MyIntentService;

/**
 * @author lixin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private MyTask task;
    private MyHandlerThread myHandlerThread;
    private Handler mHandler;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.async_task_start_btn).setOnClickListener(this);
        findViewById(R.id.async_task_cancel_btn).setOnClickListener(this);
        findViewById(R.id.intent_service_start_btn).setOnClickListener(this);
        findViewById(R.id.intent_service_cancel_btn).setOnClickListener(this);
        findViewById(R.id.handler_thread_start_btn).setOnClickListener(this);
        findViewById(R.id.handler_thread_cancel_btn).setOnClickListener(this);

        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.async_task_start_btn) {
            Toast.makeText(this, "AsyncTask Start", Toast.LENGTH_SHORT).show();
            createNewTask();
            task.execute("开始执行");
        } else if (id == R.id.async_task_cancel_btn) {
            task.cancel(false);
            Toast.makeText(this, "AsyncTask Cancel", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.intent_service_start_btn) {
            Intent intent1 = new Intent(MainActivity.this, MyIntentService.class);
            intent1.putExtra("tag", "task1");
            startService(intent1);

            Intent intent2 = new Intent(MainActivity.this, MyIntentService.class);
            intent2.putExtra("tag", "task2");
            startService(intent2);

            Toast.makeText(this, "IntentService Start", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.intent_service_cancel_btn) {
            stopService();
            Toast.makeText(this, "IntentService Cancel", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.handler_thread_start_btn) {
            createHandlerThread();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "start");
                    try {
                        Log.e(TAG, "正在执行...");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, "end");
                }
            }, 100);
            Toast.makeText(this, "Handler Thread Start", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.handler_thread_cancel_btn) {
            myHandlerThread.quit();
//            myHandlerThread.quitSafely();
            Toast.makeText(this, "Handler Thread Cancel", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        task.cancel(true);
        myHandlerThread.quit();
        stopService();
    }

    private void createHandlerThread() {
        if (myHandlerThread != null && myHandlerThread.isAlive()) {
            return;
        }
        Log.e(TAG, "myHandlerThread is not alive,need to create one");
        myHandlerThread = new MyHandlerThread("HandlerThread");
        myHandlerThread.start();
        mHandler = new Handler(myHandlerThread.getLooper());
    }

    private void stopService() {
        // 会把正在执行的任务完成
        Intent intent = new Intent(this, MyIntentService.class);
        this.stopService(intent);
    }

    private void createNewTask() {
        task = new MyTask();
        task.setListener(new AbstractTask.MyTaskListener<Integer, String>() {
            @Override
            public void onTaskStart() {
                progressBar.setProgress(0);
            }

            @Override
            public void onTaskFinished(String result) {
                Toast.makeText(MainActivity.this, "下载完成 - " + result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTaskCancelled() {
                Log.e(TAG, "Task is canceled");
                progressBar.setProgress(0);
            }

            @Override
            public void onTaskUpdate(Integer... values) {
                if (values != null) {
                    progressBar.setProgress(values[0]);
                }
            }
        });
    }
}
