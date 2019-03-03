package wang.ralf.handler_practice;

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
        findViewById(R.id.error_button).setOnClickListener(this);
        findViewById(R.id.correct_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        switch (id) {
            case R.id.error_button:
                intent = new Intent(MainActivity.this, ErrorUseActivity.class);
                break;
            case R.id.correct_button:
                intent = new Intent(MainActivity.this, CorrectUseActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }
}
