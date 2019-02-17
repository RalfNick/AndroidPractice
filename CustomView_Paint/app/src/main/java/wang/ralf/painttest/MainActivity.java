package wang.ralf.painttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import wang.ralf.painttest.activity.ColorActivity;
import wang.ralf.painttest.activity.ColorFilterActivity;
import wang.ralf.painttest.activity.FillPathViewActivity;
import wang.ralf.painttest.activity.MaskFilterViewActivity;
import wang.ralf.painttest.activity.PaintStyleActivity;
import wang.ralf.painttest.activity.PathEffectActivity;
import wang.ralf.painttest.activity.ShadowLayerViewActivity;
import wang.ralf.painttest.activity.TextPathViewActivity;
import wang.ralf.painttest.activity.XfermodeActivity;

/**
 * @author wanglixin
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_color).setOnClickListener(this);
        findViewById(R.id.color_filter).setOnClickListener(this);
        findViewById(R.id.xfermode_btn).setOnClickListener(this);
        findViewById(R.id.style_btn).setOnClickListener(this);
        findViewById(R.id.path_effect_btn).setOnClickListener(this);
        findViewById(R.id.shadow_layer_btn).setOnClickListener(this);
        findViewById(R.id.mask_filter_btn).setOnClickListener(this);
        findViewById(R.id.get_fill_path_btn).setOnClickListener(this);
        findViewById(R.id.text_path_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = null;
        switch (id) {
            case R.id.button_color:
                intent = new Intent(MainActivity.this, ColorActivity.class);
                break;
            case R.id.color_filter:
                intent = new Intent(MainActivity.this, ColorFilterActivity.class);
                break;
            case R.id.xfermode_btn:
                intent = new Intent(MainActivity.this, XfermodeActivity.class);
                break;
            case R.id.style_btn:
                intent = new Intent(MainActivity.this, PaintStyleActivity.class);
                break;
            case R.id.path_effect_btn:
                intent = new Intent(MainActivity.this, PathEffectActivity.class);
                break;
            case R.id.shadow_layer_btn:
                intent = new Intent(MainActivity.this, ShadowLayerViewActivity.class);
                break;
            case R.id.mask_filter_btn:
                intent = new Intent(MainActivity.this, MaskFilterViewActivity.class);
                break;
            case R.id.get_fill_path_btn:
                intent = new Intent(MainActivity.this, FillPathViewActivity.class);
                break;
            case R.id.text_path_btn:
                intent = new Intent(MainActivity.this, TextPathViewActivity.class);
                break;
            default:
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
