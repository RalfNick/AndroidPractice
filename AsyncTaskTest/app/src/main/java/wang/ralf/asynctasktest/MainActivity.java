package wang.ralf.asynctasktest;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author wanglixin
 */
public class MainActivity extends AppCompatActivity {

    private DownLoadDialog mDownLoadDialog;
    private MyAsyncTask mAsyncTask;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.start_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initDialog();
//                startNewTask();
//                startNewTask1();
                startTaskInNewThread();
            }
        });
    }

    private void initDialog() {
        mDownLoadDialog = new DownLoadDialog(this);
        mDownLoadDialog.setCancelable(false);
        mDownLoadDialog.updateProgress(0);
        mDownLoadDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mAsyncTask.cancel(true);
            }
        });
    }

    /**
     * 主线程中开启 AsyncTask
     */
    private void startNewTask() {
        mAsyncTask = MyAsyncTask.newTask();
        mAsyncTask.setCallBack(new MyAsyncTask.TaskCallBack() {
            @Override
            public void onPrepare() {
                mDownLoadDialog.show();
            }

            @Override
            public void onUpdate(Float[] values) {
                mDownLoadDialog.updateProgress(values[0]);
            }

            @Override
            public void onCancelled(String s) {
                Toast.makeText(MainActivity.this, "已经取消下载 - " + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinished(String s) {
                if (mDownLoadDialog.isShowing()) {
                    mDownLoadDialog.dismiss();
                }
                Toast.makeText(MainActivity.this, "下载完成 - " + s, Toast.LENGTH_SHORT).show();
            }
        });
        mAsyncTask.execute("开始下载");
    }

    /**
     * 子线程中开启 AsyncTask
     */
    private void startTaskInNewThread() {
        Thread asyncThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mAsyncTask = MyAsyncTask.newTask();
                mAsyncTask.setCallBack(new MyAsyncTask.TaskCallBack() {
                    @Override
                    public void onPrepare() {
                        mDownLoadDialog.show();
                    }

                    @Override
                    public void onUpdate(Float[] values) {
                        mDownLoadDialog.updateProgress(values[0]);
                    }

                    @Override
                    public void onCancelled(String s) {
                        Toast.makeText(MainActivity.this, "已经取消下载 - " + s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinished(String s) {
                        if (mDownLoadDialog.isShowing()) {
                            mDownLoadDialog.dismiss();
                        }
                        Toast.makeText(MainActivity.this, "下载完成 - " + s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        asyncThread.start();
        // 等待mAsyncTask创建完毕，否则 mAsyncTask.execute("开始下载"); 无法执行，空指针
        try {
            asyncThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mAsyncTask.execute("开始下载");
    }

    private void startNewTask1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
//                Looper.prepare();
                mAsyncTask = MyAsyncTask.newTask();
                mAsyncTask.setCallBack(new MyAsyncTask.TaskCallBack() {
                    @Override
                    public void onPrepare() {
                        // 子线程执行会报错
//                        mDownLoadDialog.show();
                        // mDownLoadDialog.show(); 换成 button.setText("123");
//                        button.setText("123");
                        // 切换到 UI 线程
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mDownLoadDialog.show();
                            }
                        });
                    }

                    @Override
                    public void onUpdate(final Float[] values) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mDownLoadDialog.updateProgress(values[0]);
                            }
                        });
                    }

                    @Override
                    public void onCancelled(String s) {
                        Toast.makeText(MainActivity.this, "已经取消下载 - " + s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFinished(String s) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (mDownLoadDialog.isShowing()) {
                                    mDownLoadDialog.dismiss();
                                }
                            }
                        });
                        Toast.makeText(MainActivity.this, "下载完成 - " + s, Toast.LENGTH_SHORT).show();
                    }
                });
                mAsyncTask.execute("开始下载");
//                Looper.loop();
            }
        }).start();
    }
}
