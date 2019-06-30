package wang.ralf.vieweventtest.util;

import android.app.ActivityManager;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import wang.ralf.vieweventtest.R;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name AppUtil
 * @email -
 * @date 2019/05/15 10:55
 **/
public class AppUtil {

    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DisplayMetrics getScreenMetrics(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }

    public static void executeInThread(Runnable runnable) {
        new Thread(runnable).start();
    }

    public static int getCustomizedColor(Context context) {
        int[] customizedColors = context.getResources().getIntArray(R.array.customizedColors);
        return customizedColors[new Random().nextInt(customizedColors.length)];
    }
}
