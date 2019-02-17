package wang.ralf.painttest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LinearShaderColorView
 * @email -
 * @date 2019/01/22 上午8:35
 **/
public class SweepShaderColorView extends View {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public SweepShaderColorView(Context context) {
        super(context);
    }

    public SweepShaderColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SweepShaderColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        paint.setShader(new SweepGradient(200,200,Color.parseColor("#E91E63"),
                Color.parseColor("#2196F3")));

        canvas.drawCircle(200,200,200,paint);
    }
}
