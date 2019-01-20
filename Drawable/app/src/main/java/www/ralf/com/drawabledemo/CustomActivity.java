package www.ralf.com.drawabledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import www.ralf.com.drawabledemo.widget.FeaturedPersonView;
import www.ralf.com.drawabledemo.widget.ImageTextView;

public class CustomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        final ImageTextView imageTextView = findViewById(R.id.imageTextView);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageTextView.setData("HelloWorld",R.mipmap.animal);
            }
        });

        FeaturedPersonView featuredPersonView = findViewById(R.id.featured_person_view);
        Map<Integer, String> map = new HashMap<>(10);
        for (int i = 0; i < 17; i++) {
            map.put(i,"123");
        }
        featuredPersonView.setHeadPortaitData(map);
        featuredPersonView.setClickListener(new FeaturedPersonView.OnHeadPortraitClickListener() {
            @Override
            public void onClick(int userId) {
                Toast.makeText(CustomActivity.this,"" + userId,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
