package com.ralf.www.customviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PointView
 * @email -
 * @date 2018/12/20 下午11:20
 **/
public class PointView extends View {

    private static final int[] COLORS = {Color.BLUE, Color.YELLOW, Color.RED, Color.GREEN};

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(40);
        for (int i = 0; i < 4; i++) {
            paint.setColor(COLORS[i]);
            if (i % 2 == 0) {
                canvas.drawPoint(100 * i + 40, 200, paint);
            } else {
                canvas.drawPoint(100, 100 * i, paint);
            }
        }
    }
}
