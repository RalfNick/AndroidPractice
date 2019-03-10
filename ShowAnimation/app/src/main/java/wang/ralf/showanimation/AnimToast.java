package wang.ralf.showanimation;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import wang.ralf.showanimation.util.SizeUtils;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name AnimToast
 * @email -
 * @date 2019/03/10 下午1:55
 **/
public class AnimToast {

    private static WeakReference<Toast> sWeakToast;
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    private static final int COLOR_DEFAULT = 0xFEFFFFFF;
    private static int sGravity = -1;
    private static int sXOffset = -1;
    private static int sYOffset = -1;
    private static int sBgColor = COLOR_DEFAULT;
    private static int sBgResource = -1;
    private static int sMsgColor = COLOR_DEFAULT;
    private static int sMsgTextSize = -1;

    private AnimToast() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    /**
     * Cancel the toast.
     */
    public static void cancel() {
        final Toast toast;
        if (sWeakToast != null && (toast = sWeakToast.get()) != null) {
            toast.cancel();
            sWeakToast = null;
        }
    }

    /**
     * reset default position
     */
    public static void resetDefaultPosition(Context context) {
        setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,
                0, SizeUtils.dip2px(context, 24));
    }

    /**
     * Show custom toast for a short period of time.
     *
     * @param layoutId ID for an XML layout resource to load.
     */
    public static View showCustomShort(Context context, @LayoutRes final int layoutId) {
        final View view = getView(context, layoutId);
        show(context, view, Toast.LENGTH_SHORT);
        return view;
    }

    /**
     * Show custom toast for a long period of time.
     *
     * @param layoutId ID for an XML layout resource to load.
     */
    public static View showCustomLong(Context context, @LayoutRes final int layoutId) {
        final View view = getView(context, layoutId);
        show(context, view, Toast.LENGTH_LONG);
        return view;
    }

    public static void show(Context context, final View view, final int duration) {
        HANDLER.post(() -> {
            cancel();
            final Toast toast = new Toast(context);
            sWeakToast = new WeakReference<>(toast);

            toast.setView(view);
            toast.setDuration(duration);
            if (sGravity != -1 || sXOffset != -1 || sYOffset != -1) {
                toast.setGravity(sGravity, sXOffset, sYOffset);
            }
            toast.show();
        });
    }

    /**
     * Set the gravity.
     *
     * @param gravity The gravity.
     * @param xOffset X-axis offset, in pixel.
     * @param yOffset Y-axis offset, in pixel.
     */
    public static void setGravity(final int gravity, final int xOffset, final int yOffset) {
        sGravity = gravity;
        sXOffset = xOffset;
        sYOffset = yOffset;
    }

    private static View getView(Context context, @LayoutRes final int layoutId) {
        LayoutInflater inflate =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflate != null ? inflate.inflate(layoutId, null) : null;
    }
}
