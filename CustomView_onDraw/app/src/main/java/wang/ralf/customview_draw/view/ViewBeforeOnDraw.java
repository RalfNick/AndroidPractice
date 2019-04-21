package wang.ralf.customview_draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.Layout;
import android.util.AttributeSet;

import wang.ralf.customview_draw.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ViewAfterOnDraw
 * @email -
 * @date 2019/04/21 上午10:23
 **/
public class ViewBeforeOnDraw extends AppCompatTextView {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ViewBeforeOnDraw(Context context) {
        super(context);
    }

    public ViewBeforeOnDraw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewBeforeOnDraw(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setColor(getResources().getColor(R.color.colorAccent));
        canvas.drawRect(0,0,getWidth(),getHeight(), paint);
        super.onDraw(canvas);
    }
}
