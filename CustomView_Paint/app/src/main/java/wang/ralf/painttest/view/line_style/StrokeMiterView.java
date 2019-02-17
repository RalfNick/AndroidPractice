package wang.ralf.painttest.view.line_style;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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
public class StrokeMiterView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Path path = new Path();

    public StrokeMiterView(Context context) {
        super(context);
    }

    public StrokeMiterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeMiterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);

        path.rLineTo(200, 0);
        path.rLineTo(-160, 120);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();

        canvas.translate(100, 100);
        paint.setStrokeMiter(1);
        canvas.drawPath(path, paint);

        canvas.translate(100, 300);
        paint.setStrokeMiter(3);
        canvas.drawPath(path, paint);

        canvas.translate(100, 300);
        paint.setStrokeMiter(5);
        canvas.drawPath(path, paint);

        canvas.restore();
    }
}
