package wang.ralf.painttest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
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
public class ComposeShaderColorView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public ComposeShaderColorView(Context context) {
        super(context);
    }

    public ComposeShaderColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ComposeShaderColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // 关闭硬件加速，否则看不到效果
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.batman);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.batman_logo);
        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        paint.setShader(new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER));

        canvas.drawCircle(200, 200, 200, paint);
    }
}
