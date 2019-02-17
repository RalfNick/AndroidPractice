package wang.ralf.painttest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import wang.ralf.painttest.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name XfermodeView
 * @email -
 * @date 2019/02/17 上午10:32
 **/
public class XfermodeView1 extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap1;
    Bitmap bitmap2;
    Xfermode xfermode1 = new PorterDuffXfermode(PorterDuff.Mode.SRC);
    Xfermode xfermode2 = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    Xfermode xfermode3 = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);

    public XfermodeView1(Context context) {
        super(context);
    }

    public XfermodeView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XfermodeView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.batman_logo);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 意想不到的效果
        canvas.drawBitmap(bitmap1, 0, 0, paint);
        paint.setXfermode(xfermode1);
        canvas.drawBitmap(bitmap2, 0, 0, paint);

        canvas.drawBitmap(bitmap1, bitmap1.getWidth() + 100, 0, paint);
        paint.setXfermode(xfermode2);
        canvas.drawBitmap(bitmap2, bitmap1.getWidth() + 100, 0, paint);

        canvas.drawBitmap(bitmap1, 0, bitmap1.getHeight() + 20, paint);
        paint.setXfermode(xfermode3);
        canvas.drawBitmap(bitmap2, 0, bitmap1.getHeight() + 20, paint);
    }
}
