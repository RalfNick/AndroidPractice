package wang.ralf.animationtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author lixin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.value_btn).setOnClickListener(this);
        findViewById(R.id.object_btn).setOnClickListener(this);
        findViewById(R.id.view_property_btn).setOnClickListener(this);
        findViewById(R.id.animator_set_btn).setOnClickListener(this);
        findViewById(R.id.sample_btn).setOnClickListener(this);
        findViewById(R.id.transition_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
        if (id == R.id.value_btn) {
            intent = new Intent(this, ValueAnimatorActivity.class);
        } else if (id == R.id.object_btn) {
            intent = new Intent(this, ObjectAnimatorActivity.class);
        } else if (id == R.id.view_property_btn) {
            intent = new Intent(this, ViewPropertyAnimatorActivity.class);
        } else if (id == R.id.animator_set_btn) {
            intent = new Intent(this, AnimatorSetActivity.class);
        } else if (id == R.id.sample_btn) {
            intent = new Intent(this, AnimatorSampleActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
