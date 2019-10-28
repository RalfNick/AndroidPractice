package wang.ralf.vieweventtest;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FragmentContainerActivity extends AppCompatActivity {

    public static final String TAG = FragmentContainerActivity.class.getSimpleName();
    private ArrayList<Fragment> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);
        ViewPager viewPager = findViewById(R.id.view_pager);
        ResultFragment fragment1 = ResultFragment.newInstance("", "");
        ResultFragment fragment2 = ResultFragment.newInstance("", "");
        list.add(fragment1);
        list.add(fragment2);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "收到结果 --- > FragmentContainerActivity -- requestCode " + requestCode + " resultCode " + resultCode);
        if (requestCode == 100) {
            if (resultCode == 200) {
                String result = data.getStringExtra("result");
                Log.e(TAG, "收到结果 --- > " + result);
            }
        }
    }

    private class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
