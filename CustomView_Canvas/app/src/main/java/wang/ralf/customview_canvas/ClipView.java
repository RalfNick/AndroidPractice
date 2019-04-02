package wang.ralf.customview_canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ClipView
 * @email -
 * @date 2019/03/31 下午10:22
 **/
public class ClipView extends View {

    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Bitmap bitmap;
    Path path1 = new Path();
    Path path2 = new Path();

    public ClipView(Context context) {
        super(context);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = getBitmap(context, 300, 400, R.mipmap.ic_launcher);
    }

    public ClipView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.save();
        // 裁剪是裁掉的部分，也就是说原图 - 这部分
        canvas.clipOutRect(0, 200, 300, 400);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

        Point point1 = new Point( 150,  200);
        Point point2 = new Point( 150,  200);

        path1.addCircle(point1.x,point1.y,150,Path.Direction.CW);
        path2.addCircle(point1.x,point1.y,150,Path.Direction.CCW);
        path2.setFillType(Path.FillType.INVERSE_WINDING);
        // path1
//        canvas.save();
//        // 裁剪是裁掉的部分，也就是说原图 - 这部分
//        canvas.clipPath(path1);
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//        canvas.restore();

        // path2
        canvas.save();
        // 裁剪是裁掉的部分，也就是说原图 - 这部分
        canvas.clipPath(path2);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        canvas.restore();

    }

    private static Bitmap getBitmap(Context context, int width, int height, int vectorDrawableId) {
        Bitmap bitmap = null;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Drawable vectorDrawable = context.getDrawable(vectorDrawableId);
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
        } else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), vectorDrawableId);
        }
        return bitmap;
    }
}
