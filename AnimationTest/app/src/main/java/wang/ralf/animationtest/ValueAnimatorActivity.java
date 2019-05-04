package wang.ralf.animationtest;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import wang.ralf.animationtest.entity.MyPoint;
import wang.ralf.animationtest.evaluator.PointEvaluator;

/**
 * @author wanglixin
 */
public class ValueAnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_value_animator);
        final TextView textView = findViewById(R.id.text_view);
        final ValueAnimator valueAnimator1 = ValueAnimator.ofInt(16, 48, 10);
        // ofInt 动画
        // 1.代码方式
        valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                textView.setTextSize(animatedValue);
            }
        });
        // 2.XML 方式
        final ValueAnimator animator = (ValueAnimator) AnimatorInflater.loadAnimator(ValueAnimatorActivity.this, R.animator.value_animator);
        animator.setTarget(textView);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                textView.setTextSize(animatedValue);
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 2.代码方式
//                valueAnimator1.setDuration(4000);
//                valueAnimator1.start();

                // 1.XML 方式
                animator.start();
            }
        });

        // ofObject 动画
        final ValueAnimator valueAnimator2 = ValueAnimator.ofObject(new PointEvaluator(),
                new MyPoint(30, 30), new MyPoint(500, 500));
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                MyPoint myPoint = (MyPoint) animation.getAnimatedValue();

                textView.layout(myPoint.getX(), myPoint.getY(),
                        myPoint.getX() + textView.getWidth(), myPoint.getY() + textView.getHeight());
            }
        });
        findViewById(R.id.of_object_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueAnimator2.setDuration(4000);
                valueAnimator2.start();
            }
        });
    }
}
