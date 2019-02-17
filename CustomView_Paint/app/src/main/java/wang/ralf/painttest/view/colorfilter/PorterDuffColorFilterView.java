package wang.ralf.painttest.view.colorfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import wang.ralf.painttest.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LightingColorFilterView
 * @email -
 * @date 2019/02/12 上午8:33
 **/
public class PorterDuffColorFilterView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private ColorFilter colorFilter;

    public PorterDuffColorFilterView(Context context) {
        this(context, null);
    }

    public PorterDuffColorFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PorterDuffColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
        int color = Color.parseColor("#20ee0000");
        colorFilter = new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint.setColorFilter(colorFilter);
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }
}
