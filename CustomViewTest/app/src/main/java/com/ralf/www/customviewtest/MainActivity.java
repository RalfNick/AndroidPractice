package com.ralf.www.customviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.ralf.www.customviewtest.activity.BitmapActivity;
import com.ralf.www.customviewtest.activity.CircleActivity;
import com.ralf.www.customviewtest.activity.ColorActivity;
import com.ralf.www.customviewtest.activity.LineActivity;
import com.ralf.www.customviewtest.activity.PathActivity;
import com.ralf.www.customviewtest.activity.PointActivity;
import com.ralf.www.customviewtest.activity.RectActivity;
import com.ralf.www.customviewtest.activity.TextActivity;
import com.ralf.www.customviewtest.view.ColorView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_color).setOnClickListener(this);
        findViewById(R.id.button_line).setOnClickListener(this);

        findViewById(R.id.button_point).setOnClickListener(this);
        findViewById(R.id.button_rect).setOnClickListener(this);

        findViewById(R.id.button_circle).setOnClickListener(this);
        findViewById(R.id.button_text).setOnClickListener(this);

        findViewById(R.id.button_bitmap).setOnClickListener(this);
        findViewById(R.id.button_path).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_color:
                Intent intent1 = new Intent(MainActivity.this, ColorActivity.class);
                startActivity(intent1);
                break;

            case R.id.button_point:
                Intent intent2 = new Intent(MainActivity.this, PointActivity.class);
                startActivity(intent2);
                break;

            case R.id.button_line:
                Intent intent3 = new Intent(MainActivity.this, LineActivity.class);
                startActivity(intent3);
                break;

            case R.id.button_rect:
                Intent intent4 = new Intent(MainActivity.this, RectActivity.class);
                startActivity(intent4);
                break;

            case R.id.button_circle:
                Intent intent5 = new Intent(MainActivity.this, CircleActivity.class);
                startActivity(intent5);
                break;

            case R.id.button_text:
                Intent intent6 = new Intent(MainActivity.this, TextActivity.class);
                startActivity(intent6);
                break;

            case R.id.button_bitmap:
                Intent intent7 = new Intent(MainActivity.this, BitmapActivity.class);
                startActivity(intent7);
                break;

            case R.id.button_path:
                Intent intent8 = new Intent(MainActivity.this, PathActivity.class);
                startActivity(intent8);
                break;
            default:
                break;
        }
    }
}
