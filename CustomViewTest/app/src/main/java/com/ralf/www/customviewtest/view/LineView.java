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
 * @name LineView
 * @email -
 * @date 2018/12/20 下午11:20
 **/
public class LineView extends View {

    private static final int[] COLORS = {Color.BLUE, Color.YELLOW, Color.RED, Color.GREEN};

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        for (int i = 0; i < 8; i++) {
            paint.setColor(COLORS[i % 4]);
            paint.setStrokeWidth(10 * (i + 1));
            canvas.drawLine(50, 50 * (i + 1), 100 + 50 * i, 50 * (i + 1), paint);
        }
    }
}
