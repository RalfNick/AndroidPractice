package wang.ralf.animationtest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * @author wanglixin
 */
public class AnimatorSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator_sample);

        TextView textView = findViewById(R.id.hello_world);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(textView, "scaleX", 0.45f, 1);
        animator1.setRepeatMode(ValueAnimator.RESTART);
        animator1.setRepeatCount(ObjectAnimator.INFINITE);
        animator1.setDuration(3000);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(textView, "scaleY", 0.45f, 1);
        animator2.setRepeatMode(ValueAnimator.RESTART);
        animator2.setRepeatCount(ObjectAnimator.INFINITE);
        animator2.setDuration(3000);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(textView, "alpha", 0, 1);
        animator3.setRepeatMode(ValueAnimator.RESTART);
        animator3.setRepeatCount(ObjectAnimator.INFINITE);
        animator3.setDuration(3000);
        animatorSet.playTogether(animator1, animator2, animator3);
        animatorSet.start();
    }
}
