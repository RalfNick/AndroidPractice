package wang.ralf.painttest.view.colorfilter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
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
public class LightingColorFilterView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap bitmap;
    private ColorFilter colorFilter1 = new LightingColorFilter(0xff00ff, 0x000000);
    private ColorFilter colorFilter2 = new LightingColorFilter(0xffffff, 0x003000);


    public LightingColorFilterView(Context context) {
        this(context,null);
    }

    public LightingColorFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LightingColorFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint.setColorFilter(colorFilter1);
//        paint.setColorFilter(colorFilter2);
        canvas.drawBitmap(bitmap,0,0,paint);
    }
}
