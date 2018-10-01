package com.ralf.www.recyclerviewtest.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ralf.www.recyclerviewtest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PictureActivity extends AppCompatActivity {


    @BindView(R.id.big_pic_imageview)
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        ButterKnife.bind(this);

        parseIntent();
    }

    private void parseIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            String extra = intent.getStringExtra("url");
            if (!TextUtils.isEmpty(extra)) {

                RequestOptions requestOptions = RequestOptions.placeholderOf(R.mipmap.ic_launcher)
                        .error(R.mipmap.ic_refresh_white_24dp)
                        .useAnimationPool(true);
                Glide.with(this)
                        .load(extra)
                        .apply(requestOptions)
                        .into(mImageView);
            }
        }
    }
}
