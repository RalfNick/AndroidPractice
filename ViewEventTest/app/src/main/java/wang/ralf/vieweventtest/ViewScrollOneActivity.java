package wang.ralf.vieweventtest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wang.ralf.vieweventtest.util.AppUtil;
import wang.ralf.vieweventtest.view.RHorizontalScrollView;

/**
 * @author wanglixin
 */
public class ViewScrollOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scroll_one);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        RHorizontalScrollView scrollView = findViewById(R.id.horizontal_scroll_view);
        final int screenWidth = AppUtil.getScreenMetrics(this).widthPixels;
        for (int i = 0; i < 5; i++) {
            LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.item_content_list_layout, scrollView, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.title);
            textView.setText("页面 " + (i + 1));
            layout.setBackgroundColor(AppUtil.getCustomizedColor(this));
            setUpChildLayout(layout);
            scrollView.addView(layout);
        }
    }

    private void setUpChildLayout(LinearLayout layout) {
        ListView listView = layout.findViewById(R.id.list_view);
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.content_list_item, R.id.item_title, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ViewScrollOneActivity.this, "click item",
                        Toast.LENGTH_SHORT).show();

            }
        });

    }
}
