package wang.ralf.showanimation;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import wang.ralf.showanimation.util.SizeUtils;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseDialog
 * @email -
 * @date 2019/02/26 下午7:33
 **/
public class PraiseDialog extends Dialog {

    private Context mContext;
    private PraiseAnimView mAnimView;
    private int mWidth;
    private int mHeight;
    private Handler mHandler;

    public PraiseDialog(Builder builder) {
        super(builder.context);
        mContext = builder.context;
        mWidth = builder.width;
        mHeight = builder.height;
        mHandler = new Handler();
    }

    public PraiseDialog(Builder builder, int themeResId) {
        super(builder.context, themeResId);
        mContext = builder.context;
        mWidth = builder.width;
        mHeight = builder.height;
        mHandler = new Handler();
    }

    protected PraiseDialog(Builder builder, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(builder.context, builder.cancelTouchOut, builder.cancelListener);
        mContext = builder.context;
        mWidth = builder.width;
        mHeight = builder.height;
        mHandler = new Handler();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(mContext, R.layout.layout_dialog_praise_animation, null);
        setContentView(view);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.height = mHeight;
        lp.width = mWidth;
        win.setAttributes(lp);
        mAnimView = view.findViewById(R.id.praise_anim_view);
    }

    @Override
    public void show() {
        super.show();
        mAnimView.startAnim();
        // 隐藏
        mHandler.postDelayed(this::dismiss, 3500);
    }

    /**
     * 设置对话框的位置，在屏幕上的位置
     *
     * @param x 横坐标
     * @param y 纵坐标
     */
    public void setPosition(int x, int y) {
        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        win.setGravity(Gravity.TOP | Gravity.START);
        lp.x = x;
        lp.y = y;
        win.setAttributes(lp);
    }

    public static final class Builder {

        private Context context;
        private int height, width;
        private boolean cancelTouchOut;
        private View view;
        private int resStyle = -1;
        private OnCancelListener cancelListener;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder heightdp(int val) {
            height = SizeUtils.dip2px(context, val);
            return this;
        }

        public Builder widthdp(int val) {
            width = SizeUtils.dip2px(context, val);
            return this;
        }

        public Builder heightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder cancelTouchout(boolean val) {
            cancelTouchOut = val;
            return this;
        }

        public Builder cancelListener(OnCancelListener listener) {
            cancelListener = listener;
            return this;
        }

        public Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }


        public PraiseDialog build() {
            if (resStyle != -1) {
                return new PraiseDialog(this, resStyle);
            } else {
                return new PraiseDialog(this);
            }
        }
    }
}
