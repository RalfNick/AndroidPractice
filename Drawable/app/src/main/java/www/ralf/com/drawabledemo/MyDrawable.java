package www.ralf.com.drawabledemo;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MyDrawable
 * @email wanglixin@icourt.cc
 * @date 2018/11/11 下午5:17
 **/
public class MyDrawable extends Drawable {

    private Paint mPaint;
    private int mPaintColor;

    public MyDrawable(int paintColor) {
        mPaintColor = paintColor;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mPaintColor);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect r = getBounds();
        float centerX = r.exactCenterX();
        float centerY = r.exactCenterY();
        canvas.drawCircle(centerX,centerY,Math.min(centerX,centerY),mPaint);
    }

    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicWidth()
    {
        return 100;
    }

    @Override
    public int getIntrinsicHeight()
    {
        return 100;
    }

}
