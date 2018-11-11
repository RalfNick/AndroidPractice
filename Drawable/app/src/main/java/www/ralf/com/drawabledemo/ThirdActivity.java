package www.ralf.com.drawabledemo;

import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        TextView textView = findViewById(R.id.text_view);
        TransitionDrawable drawable = (TransitionDrawable) textView.getBackground();
        drawable.startTransition(2000);

        ImageView clipImageView = findViewById(R.id.imageView5);
        ClipDrawable clipDrawable = (ClipDrawable) clipImageView.getDrawable();
        clipDrawable.setLevel(5000);

        ImageView imageView6 = findViewById(R.id.imageView6);
        MyDrawable myDrawable = new MyDrawable(0xff255779);

        imageView6.setImageDrawable(myDrawable);
    }
}
