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
public class StrokeWidthView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public StrokeWidthView(Context context) {
        super(context);
    }

    public StrokeWidthView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeWidthView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint.setStrokeWidth(0);
        paint.setColor(Color.parseColor("#009688"));
        canvas.drawCircle(150,180,100,paint);

        paint.setStrokeWidth(20);
        canvas.drawCircle(400,180,100,paint);
    }
}
