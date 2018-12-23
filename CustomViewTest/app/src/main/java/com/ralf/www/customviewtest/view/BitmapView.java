package com.ralf.www.customviewtest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.ralf.www.customviewtest.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RectView
 * @email -
 * @date 2018/12/20 下午11:20
 **/
public class BitmapView extends View {

    public BitmapView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.bitmap);
        canvas.drawBitmap(bitmap,200,500,new Paint());
    }
}
