package com.ralf.www.customviewtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ColorView
 * @email -
 * @date 2018/12/20 下午11:12
 **/
public class ColorView extends View {

    private static final int[] COLORS = {Color.BLUE,Color.YELLOW,Color.RED,Color.GREEN};
    private int mColor;
    private Handler mHandler;
    private Random random = new Random(17);

    public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                int what = msg.what;
                if (what == 1){
                    invalidate();
                }
                return true;
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){

                    try {
                        Thread.sleep(2000);
                        Message message = new Message();
                        message.what = 1;
                        mHandler.sendMessage(message);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mColor = COLORS[random.nextInt(4)];
//        mColor = Color.argb(127, 255, 0, 0);
        canvas.drawColor(mColor);

    }
}
