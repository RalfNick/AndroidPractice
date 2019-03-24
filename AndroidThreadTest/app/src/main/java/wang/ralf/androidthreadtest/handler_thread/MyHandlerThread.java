package wang.ralf.androidthreadtest.handler_thread;

import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MyHandlerThread
 * @email -
 * @date 2019/03/18 下午3:43
 **/
public class MyHandlerThread extends HandlerThread {

    private static final String TAG = MyHandlerThread.class.getSimpleName();

    public MyHandlerThread(String name) {
        super(name);
    }

    public MyHandlerThread(String name, int priority) {
        super(name, priority);
    }

    @Override
    protected void onLooperPrepared() {
        super.onLooperPrepared();
        Log.e(TAG,"onLooperPrepared");
    }

    @Override
    public void run() {
        Log.e(TAG,"run");
        super.run();
    }

    @Override
    public Looper getLooper() {
        return super.getLooper();
    }

    @Override
    public boolean quit() {
        Log.e(TAG,"quit");
        return super.quit();
    }
}
