package wang.ralf.showanimation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import wang.ralf.showanimation.util.SizeUtils;

/**
 * @author wanglixin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mDialogBtn;
    private Button mToastBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDialogBtn = findViewById(R.id.dialog_btn);
        mToastBtn = findViewById(R.id.toast_btn);

        mDialogBtn.setOnClickListener(this);
        mToastBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.dialog_btn) {
            int dialogBtnWidth = mDialogBtn.getWidth();
            int height = mDialogBtn.getHeight();
            //获取在整个屏幕内的绝对坐标
            int[] location = new int[2];
            mDialogBtn.getLocationInWindow(location);
            PraiseDialog dialog = new PraiseDialog.Builder(this)
                    .heightdp(200)
                    .widthdp(160)
                    .style(R.style.TransparentDlgTheme)
                    .cancelTouchout(true)
                    .build();
            dialog.setPosition(location[0] + dialogBtnWidth,
                    location[1] - SizeUtils.dip2px(this, 200) - height / 2);
            dialog.show();
        } else if (id == R.id.toast_btn) {
            int toastBtnWidth = mToastBtn.getWidth();
            int height = mToastBtn.getHeight();
            //获取在整个屏幕内的绝对坐标
            int[] location = new int[2];
            mToastBtn.getLocationInWindow(location);
            int x = location[0] + toastBtnWidth;
            int y = location[1] - SizeUtils.dip2px(this, 200) - height / 2;
            AnimToast.setGravity(Gravity.START | Gravity.TOP, x, y);
            View view = AnimToast.showCustomLong(this, R.layout.layout_toast_praise_animation);
            PraiseAnimView praiseAnimView = view.findViewById(R.id.praise_anim_view);
            praiseAnimView.startAnim();
        }
    }
}
