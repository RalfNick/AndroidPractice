package wang.ralf.customview_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CustomViewByMatrix
 * @email -
 * @date 2019/03/31 上午11:11
 **/
public class CustomViewByMatrix extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomViewByMatrix(Context context) {
        super(context);
    }

    public CustomViewByMatrix(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomViewByMatrix(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomViewByMatrix(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
//        Matrix matrix = new Matrix();
//        matrix.postTranslate(200,200);
//        canvas.setMatrix(matrix);
//        // = canvas.translate(200, 200);

//        mPaint.setColor(Color.BLUE);
//        canvas.drawCircle(0, 0, 100, mPaint);

        for (int i = 0; i < 20; i++) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(Color.BLUE);
            mPaint.setStrokeWidth(3);
            Matrix matrix = new Matrix();
            matrix.postScale(0.9f, 0.9f);
            canvas.concat(matrix);
            canvas.drawCircle(0, 0, 400, mPaint);
        }
    }
}
