package wang.ralf.vieweventtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import wang.ralf.vieweventtest.util.AppUtil;
import wang.ralf.vieweventtest.view.RHorizontalScrollView;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MeasureActivity
 * @email -
 * @date 2019/05/15 10:23
 **/
public class MeasureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_view);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        RHorizontalScrollView scrollView = findViewById(R.id.horizontal_scroll_view);
        final int screenWidth = AppUtil.getScreenMetrics(this).widthPixels;
        for (int i = 0; i < 5; i++) {
            LinearLayout childView = (LinearLayout) inflater.inflate(R.layout.item_content_layout, scrollView,false);
            childView.getLayoutParams().width = screenWidth;
            scrollView.addView(childView);
        }
    }
}
