package wang.ralf.painttest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import wang.ralf.painttest.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LinearShaderColorView
 * @email -
 * @date 2019/01/22 上午8:35
 **/
public class BitmapShaderColorView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public BitmapShaderColorView(Context context) {
        super(context);
    }

    public BitmapShaderColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapShaderColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.batman_small);
//        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        // 共有九种组合
//        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.MIRROR, Shader.TileMode.MIRROR));

        canvas.drawRect(0, 0, 400,400, paint);
    }
}
