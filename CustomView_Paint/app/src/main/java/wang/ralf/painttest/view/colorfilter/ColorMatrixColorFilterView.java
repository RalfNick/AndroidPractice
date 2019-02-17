package wang.ralf.painttest.view.colorfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
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
public class ColorMatrixColorFilterView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private ColorFilter colorFilter;

    public ColorMatrixColorFilterView(Context context) {
        this(context,null);
    }

    public ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorMatrixColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
        ColorMatrix colorMatrix = new ColorMatrix();
//        colorMatrix.setSaturation(1);
        colorMatrix.setRotate(1,30);
        colorFilter = new ColorMatrixColorFilter(colorMatrix);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint.setColorFilter(colorFilter);
        canvas.drawBitmap(bitmap,0,0,paint);
    }
}
