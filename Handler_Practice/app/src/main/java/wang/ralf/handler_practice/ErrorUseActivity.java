package wang.ralf.handler_practice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author wanglixin
 */
public class ErrorUseActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_use);
        mTextView = findViewById(R.id.text_view);
        mTextView.setText("Hello World!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mTextView.setText("Hello Error!");
            }
        }).start();

        /**
         * 这种情况下会怎么样？这里有一个疑问，后面会进行分析
         */
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    mTextView.setText("Hello Error!");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
}
