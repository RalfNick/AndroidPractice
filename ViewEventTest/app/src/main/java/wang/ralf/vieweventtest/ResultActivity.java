package wang.ralf.vieweventtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ResultActivity extends AppCompatActivity {

    public static final String TAG = ResultActivity.class.getSimpleName();
    private int mRequestCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        findViewById(R.id.return_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("result", "you got the result ");
                int resultCode = -1;
                if (mRequestCode == 100) {
                    resultCode = 200;
                } else if (mRequestCode == 101) {
                    resultCode = 300;
                } else if (mRequestCode == 102) {
                    resultCode = 400;
                } else if (mRequestCode == 103) {
                    resultCode = 500;
                }
                // 测试不设置 setResult，看 calling 是否调用 onActivityResult
                if (resultCode != 500) {
                    setResult(resultCode, intent);
                }

                finish();
            }
        });
        mRequestCode = getIntent().getIntExtra("code", 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed");
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    public void finish() {
        super.finish();
        Log.e(TAG, "finish");
    }
}
