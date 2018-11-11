package www.ralf.com.drawabledemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        ImageView imageView = findViewById(R.id.round_imageView);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.pic3);
        RoundImageDrawable roundImageDrawable = new RoundImageDrawable(bitmap);
        imageView.setImageDrawable(roundImageDrawable);
    }
}
