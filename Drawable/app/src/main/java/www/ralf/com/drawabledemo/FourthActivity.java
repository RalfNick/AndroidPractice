package www.ralf.com.drawabledemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

public class FourthActivity extends AppCompatActivity {

    private String mImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        ImageView imageView = findViewById(R.id.round_imageView);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic3);
        RoundImageDrawable roundImageDrawable = new RoundImageDrawable(bitmap);
        imageView.setImageDrawable(roundImageDrawable);

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(FourthActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .compress(true)
                        .isCamera(true)
                        .selectionMode(PictureConfig.SINGLE)
                        .imageSpanCount(4)
                        .previewImage(true)
                        .previewVideo(false)
                        .rotateEnabled(false)
                        .compressSavePath(mImagePath)
                        .isGif(false)
                        .enableCrop(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList != null && selectList.size() > 0) {
                        mImagePath = selectList.get(0).getCompressPath();
                    }

                    break;
                default:
                    break;
            }
        }
    }
}
