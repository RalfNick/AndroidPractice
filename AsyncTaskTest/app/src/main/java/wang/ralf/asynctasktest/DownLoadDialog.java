package wang.ralf.asynctasktest;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DownLoadDialog
 * @email -
 * @date 2019/06/28 21:04
 **/
public class DownLoadDialog extends Dialog {

    private Context mContext;
    private TextView mProgressTv;
    private ProgressBar mProgressBar;

    public DownLoadDialog(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public DownLoadDialog(Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
        init();
    }

    protected DownLoadDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
        init();
    }

    private void init() {
        setContentView(R.layout.dialog_down_layout);
        mProgressTv = findViewById(R.id.down_load_tv);
        Button cancelBtn = findViewById(R.id.cancel_btn);
        mProgressBar = findViewById(R.id.progress);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        setCenterLayout();
    }

    private void setCenterLayout() {
        Window window = getWindow();
        if (window != null) {
            //居中显示
            window.setGravity(Gravity.CENTER);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
        }
    }

    public void updateProgress(float value) {
        String progress = toPercent(value);
        mProgressTv.setText(progress);
        mProgressBar.setProgress((int) (value * 100));
    }

    private String toPercent(float value) {
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMaximumFractionDigits(0);
        return percentInstance.format(value);
    }
}
