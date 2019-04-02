package wang.ralf.customview_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CustomView
 * @email -
 * @date 2019/03/31 上午10:00
 **/
public class CustomView extends View {

    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        // 1.平移
//        mPaint.setColor(Color.GREEN);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100, mPaint);
//
//        mPaint.setColor(Color.BLUE);
//        canvas.translate(200,200);
//        canvas.drawCircle(0,0,100, mPaint);

        // 2.缩放
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(2);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawCircle(0, 0, 200, mPaint);
        // 缩放方法一
////        canvas.scale(2, 2);
        // 缩放方法二
//        canvas.scale(0.5f, 0.5f,0,200);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawCircle(0, 0, 200, mPaint);

        // 缩小效果
        // 将坐标系原点移动到画布正中心
//        canvas.translate(getWidth() / 2, getHeight() / 2);
//        for (int i = 0; i <= 20; i++) {
//            mPaint.setStyle(Paint.Style.STROKE);
//            mPaint.setStrokeWidth(5);
//            mPaint.setColor(Color.BLUE);
//            canvas.scale(0.9f, 0.9f);
//            canvas.drawCircle(0, 0, 400, mPaint);
//        }

        // 3.旋转
//        RectF rect = new RectF(0,-400,400,0);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawRect(rect,mPaint);

        // 默认旋转一
//        canvas.rotate(180);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rect,mPaint);

        // 旋转二
//        canvas.rotate(180,100,0);
//        mPaint.setColor(Color.BLUE);
//        canvas.drawRect(rect,mPaint);

        // 4.错切
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        RectF rect = new RectF(0, 0, 200, 200);
        mPaint.setColor(Color.BLACK);
        canvas.drawRect(rect, mPaint);

        mPaint.setColor(Color.BLUE);
        canvas.skew(1,0); // 水平错切,45°
//        canvas.skew(0,1); // 垂直错切，45°
        canvas.drawRect(rect, mPaint);
    }
}
