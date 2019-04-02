package wang.ralf.customview_canvas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author Ralf
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.clip_btn).setOnClickListener(this);
        findViewById(R.id.save_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        if (id == R.id.clip_btn) {
            intent = new Intent(MainActivity.this, ClipActivity.class);
        } else if (id == R.id.save_btn) {
            intent = new Intent(MainActivity.this, SaveAndRestoreActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
