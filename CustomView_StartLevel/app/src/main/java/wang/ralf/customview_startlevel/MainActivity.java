package wang.ralf.customview_startlevel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * @author wanglixin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int i = 1;
    private StarLevelView starLevelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        starLevelView = findViewById(R.id.star_level_view);
        starLevelView.setLevel(1);
        findViewById(R.id.upgrade_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        starLevelView.setLevel(++i % 6);
    }
}
