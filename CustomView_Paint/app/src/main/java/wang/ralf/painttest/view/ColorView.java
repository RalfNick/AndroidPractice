package wang.ralf.painttest.view;

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
 * @name ColorView
 * @email -
 * @date 2019/01/22 上午8:33
 **/
public class ColorView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ColorView(Context context) {
        super(context);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint.setColor(Color.parseColor("#009688"));
//        paint.setARGB(100, 255, 0, 0);
        canvas.drawCircle(200,200,200,paint);
    }
}
