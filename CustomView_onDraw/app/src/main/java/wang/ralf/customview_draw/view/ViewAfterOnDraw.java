package wang.ralf.customview_draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import wang.ralf.customview_draw.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ViewAfterOnDraw
 * @email -
 * @date 2019/04/21 上午10:23
 **/
public class ViewAfterOnDraw extends AppCompatImageView {

    public static final boolean DEBUG = true;

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ViewAfterOnDraw(Context context) {
        super(context);
    }

    public ViewAfterOnDraw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewAfterOnDraw(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (DEBUG) {
            // 在 debug 模式下绘制出 drawable 的尺寸信息
            Drawable drawable = getDrawable();
            if (drawable != null) {
                canvas.save();
//                canvas.concat(getImageMatrix());
                Rect bounds = drawable.getBounds();
                canvas.drawText(getResources().getString(R.string.image_size, bounds.width(), bounds.height()), 40, 60, paint);
                canvas.restore();
            }
        }
    }
}
