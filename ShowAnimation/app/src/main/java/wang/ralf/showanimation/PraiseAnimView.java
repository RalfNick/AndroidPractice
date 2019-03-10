package wang.ralf.showanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import wang.ralf.showanimation.util.BitmapUtil;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseAnimView
 * @email -
 * @date 2019/02/23 下午1:38
 **/
public class PraiseAnimView extends RelativeLayout {

    private static final int TIME_ANIMATION = 4000;
    protected PointF pointFStart, pointFEnd, pointFFirst, pointFSecond;
    private Bitmap mBitmap;
    private AnimatorSet mAnimatorSet;
    private ImageView mImageView;

    public PraiseAnimView(Context context) {
        super(context);
        init();
    }

    public PraiseAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PraiseAnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackground(getResources().getDrawable(R.mipmap.bg_praise_anim, null));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.praise_anim_pic);
        int height = Double.valueOf(bitmap.getHeight() * 1.5).intValue();
        int width = Double.valueOf(bitmap.getWidth() * 1.5).intValue();
        mBitmap = BitmapUtil.zoomImg(bitmap, width, height);
        pointFStart = new PointF();
        pointFFirst = new PointF();
        pointFSecond = new PointF();
        pointFEnd = new PointF();
        initAnim();
    }

    private void initView() {
        mImageView = new ImageView(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_HORIZONTAL);
        params.addRule(ALIGN_PARENT_BOTTOM);
        mImageView.setImageBitmap(mBitmap);
        addView(mImageView, params);
    }

    public void startAnim() {
        removeAllViews();
        initView();
        mAnimatorSet.setStartDelay(0);
        mAnimatorSet.start();
    }

    private void initAnim() {
        mAnimatorSet = new AnimatorSet();
        // 位置动画
        ValueAnimator beiAnim = ValueAnimator.ofObject(new MyTypeEvaluator(pointFFirst, pointFSecond),
                pointFStart, pointFEnd);
        beiAnim.addUpdateListener(animation -> {
            PointF value = (PointF) animation.getAnimatedValue();
            mImageView.setX(value.x - mImageView.getWidth() / 2);
            mImageView.setY(value.y + mImageView.getHeight() / 2);
        });
        beiAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        // 缩放动画
        PropertyValuesHolder pl = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.2f, 1f);
        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.2f, 1f);
        ObjectAnimator scaleAnim = ObjectAnimator.ofPropertyValuesHolder(mImageView, pl, p2).setDuration(500);
        // 透明度动画
        ObjectAnimator alphaAnim = ObjectAnimator.ofFloat(mImageView, "alpha", 0.8f, 0).setDuration(500);
        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                PraiseAnimView.this.removeView(mImageView);
            }
        });
        mAnimatorSet.setDuration(TIME_ANIMATION).play(beiAnim).with(alphaAnim).with(scaleAnim);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        pointFStart.x = mBitmap.getWidth() / 2;
        pointFStart.y = getMeasuredHeight() - mBitmap.getHeight() * 3 / 2;
        pointFEnd.y = 10;
        pointFEnd.x = getMeasuredWidth() - mBitmap.getWidth() / 2;

        pointFFirst.x = 10;
        pointFFirst.y = getMeasuredHeight() / 2;
        pointFSecond.x = getMeasuredWidth();
        pointFSecond.y = getMeasuredHeight() / 2;
    }

    /**
     * 估值器
     */
    static class MyTypeEvaluator implements TypeEvaluator<PointF> {

        private PointF pointFFirst, pointFSecond;

        public MyTypeEvaluator(PointF start, PointF end) {
            this.pointFFirst = start;
            this.pointFSecond = end;
        }

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            PointF result = new PointF();
            float left = 1 - fraction;
            result.x = (float) (startValue.x * Math.pow(left, 3) + 3 * pointFFirst.x * Math.pow(left, 2)
                    * fraction + 3 * pointFSecond.x * Math.pow(fraction, 2) * left + endValue.x * Math.pow(fraction, 3));
            result.y = (float) (startValue.y * Math.pow(left, 3) + 3 * pointFFirst.y * Math.pow(left, 2)
                    * fraction + 3 * pointFSecond.y * Math.pow(fraction, 2) * left + endValue.y * Math.pow(fraction, 3));
            return result;
        }
    }

}
