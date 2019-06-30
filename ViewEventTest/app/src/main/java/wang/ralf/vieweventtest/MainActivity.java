package wang.ralf.vieweventtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author wanglixin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.view_measure_btn).setOnClickListener(this);
        findViewById(R.id.view_scroll1_btn).setOnClickListener(this);
        findViewById(R.id.view_scroll2_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
        if (id == R.id.view_measure_btn) {
            intent = new Intent(MainActivity.this, MeasureActivity.class);
        } else if (id == R.id.view_scroll1_btn) {
            intent = new Intent(MainActivity.this, ViewScrollOneActivity.class);
        } else if (id == R.id.view_scroll2_btn) {
            intent = new Intent(MainActivity.this, ViewScrollTwoActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
