package wang.ralf.animationtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CircleView
 * @email -
 * @date 2019/05/03 11:56
 **/
public class RectView extends View {

    private Paint mPaint;
    private String color;

    public RectView(Context context) {
        super(context);
        initView();
    }

    public RectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.GREEN);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() >> 1, getHeight() >> 1);
        canvas.drawRoundRect(-100.0f, -100.0f, 100.0f, 100.0f,
                10, 10, mPaint);
    }
}
