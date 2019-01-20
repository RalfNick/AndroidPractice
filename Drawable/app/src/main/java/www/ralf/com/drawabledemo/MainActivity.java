package www.ralf.com.drawabledemo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.image_ring).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,
                        SecondActivity.class));
            }
        });

        findViewById(R.id.look_third_meizhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,
                        ThirdActivity.class));
            }
        });

        findViewById(R.id.oral_stroke).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FourthActivity.class));
            }
        });

        findViewById(R.id.CustomeView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomActivity.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "MainActivity - onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "MainActivity - onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "MainActivity - onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "MainActivity - onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "MainActivity - onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "MainActivity - onDestroy");
    }
}
