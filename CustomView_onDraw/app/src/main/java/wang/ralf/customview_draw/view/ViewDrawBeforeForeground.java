package wang.ralf.customview_draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import wang.ralf.customview_draw.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ViewLayoutDraw
 * @email -
 * @date 2019/04/21 下午3:54
 **/
public class ViewDrawBeforeForeground extends android.support.v7.widget.AppCompatImageView {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ViewDrawBeforeForeground(Context context) {
        super(context);
    }

    public ViewDrawBeforeForeground(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewDrawBeforeForeground(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setTextSize(60);
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.drawRect(0, 40, 200, 120, paint);
        paint.setColor(Color.WHITE);
        canvas.drawText("Label", 20, 100, paint);

        super.onDrawForeground(canvas);
    }
}
