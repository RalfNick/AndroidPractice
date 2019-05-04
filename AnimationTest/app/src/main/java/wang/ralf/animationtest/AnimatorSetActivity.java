package wang.ralf.animationtest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import wang.ralf.animationtest.evaluator.ColorEvaluator;
import wang.ralf.animationtest.view.RectView;

/**
 * @author wanglixin
 */
public class AnimatorSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_set);

        RectView rectView = findViewById(R.id.circle_view);
        final ObjectAnimator animator1 = ObjectAnimator.ofFloat(rectView, "scaleX", 1, 2)
                .setDuration(2000);
        final ObjectAnimator animator2 = ObjectAnimator.ofObject(rectView,
                "color", new ColorEvaluator(), "#0000FF", "#FF0000")
                .setDuration(2000);
        final ObjectAnimator animator3 = ObjectAnimator.ofFloat(rectView, "rotation", 0, 450.0f)
                .setDuration(3000);

        findViewById(R.id.start_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playSequentially(animator1, animator2, animator3);
                animatorSet.start();
            }
        });
    }
}
