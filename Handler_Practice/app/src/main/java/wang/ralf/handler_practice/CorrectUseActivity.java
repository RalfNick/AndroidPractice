package wang.ralf.handler_practice;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author wanglixin
 */
public class CorrectUseActivity extends AppCompatActivity {

    private Handler mHandler;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_use);
        mTextView = findViewById(R.id.text_view);
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        String str1 = (String) msg.obj;
                        mTextView.setText(str1);
                        break;
                    case 2:
                        String str2 = (String) msg.obj;
                        mTextView.setText(str2);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        // 无参构造情况下，不能使用 sendMessage 方法，否则不会执行，但是有参数构造情况下，可以使用 post 方法
//        mHandler = new Handler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 线程1
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = "come from Thread 1";
                    mHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 线程2
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("come from Thread 2");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
