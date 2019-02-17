package wang.ralf.painttest.view.line_style;

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
 * @name StrokeWidthView
 * @email -
 * @date 2019/02/17 下午4:31
 **/
public class StrokeCapView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public StrokeCapView(Context context) {
        super(context);
    }

    public StrokeCapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeCapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStrokeWidth(40);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(50, 50, 400, 50, paint);

        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(50, 150, 400, 150, paint);

        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(50, 250, 400, 250, paint);
    }
}
