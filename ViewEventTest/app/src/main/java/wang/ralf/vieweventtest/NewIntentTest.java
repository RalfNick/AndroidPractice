package wang.ralf.vieweventtest;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class NewIntentTest extends AppCompatActivity {

    public static final String TAG = NewIntentTest.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_intent_test);
        Log.e(TAG, "onCreate");
        findViewById(R.id.intent_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewIntentTest.this, ViewScrollTwoActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.intent_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewIntentTest.this, FragmentContainerActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.intent_btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewIntentTest.this, ResultActivity.class);
                intent.putExtra("code", 101);
                startActivityForResult(intent, 101);
            }
        });

        findViewById(R.id.intent_btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewIntentTest.this, ResultActivity.class);
                intent.putExtra("code", 102);
                // 看是否调用 onActivityResult
                startActivity(intent);
            }
        });

        findViewById(R.id.intent_btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NewIntentTest.this, ResultActivity.class);
                intent.putExtra("code", 103);
                startActivityForResult(intent, 103);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult --- > 调用了");
        if (resultCode == 300) {
            if (requestCode == 101) {
                assert data != null;
                String result = data.getStringExtra("result");
                Log.e(TAG, "onActivityResult --- > " + result);
            }
        } else if (resultCode == 400) {
            if (requestCode == 102) {
                assert data != null;
                String result = data.getStringExtra("result");
                Log.e(TAG, "onActivityResult --- > " + result);
            }
        } else if (resultCode == 500) {
            if (requestCode == 103) {
                assert data != null;
                String result = data.getStringExtra("result");
                Log.e(TAG, "onActivityResult --- > " + result);
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TAG, "onConfigurationChanged");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }
}
