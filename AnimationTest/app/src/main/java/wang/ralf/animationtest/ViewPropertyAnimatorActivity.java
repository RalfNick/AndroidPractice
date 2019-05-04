package wang.ralf.animationtest;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * @author wanglixin
 */
public class ViewPropertyAnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property_animator_acivity);

        final TextView textView = findViewById(R.id.text_view);

        findViewById(R.id.start_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.animate()
                        .translationYBy(-100)
                        .alphaBy(-0.1f)
                        .scaleX(1.5f)
                        .rotationBy(180)
                        .setDuration(4000)
                        .start();
            }
        });
    }
}
