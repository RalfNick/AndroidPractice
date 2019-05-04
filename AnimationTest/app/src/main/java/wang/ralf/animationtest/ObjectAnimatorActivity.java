package wang.ralf.animationtest;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import wang.ralf.animationtest.evaluator.ColorEvaluator;
import wang.ralf.animationtest.view.RectView;

/**
 * @author wanglixin
 */
public class ObjectAnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);
        final RectView rectView = findViewById(R.id.circle_view);
        findViewById(R.id.color_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator.ofObject(rectView, "color", new ColorEvaluator(), "#0000FF", "#FF0000")
                        .setDuration(4000)
                        .start();
            }
        });

        findViewById(R.id.rotate_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator.ofFloat(rectView, "rotation", 0, 360.0f)
                        .setDuration(4000)
                        .start();

            }
        });
    }
}
