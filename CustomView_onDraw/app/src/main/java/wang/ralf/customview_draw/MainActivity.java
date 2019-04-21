package wang.ralf.customview_draw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * @author wanglixin
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private int[] layouts = {
            R.layout.fragment_after_on_draw,
            R.layout.fragment_before_on_draw,
            R.layout.fragment_layout_ondraw,
            R.layout.fragment_layout_dispatch_draw,
            R.layout.fragment_after_draw_foreground,
            R.layout.fragment_before_draw_foreground
    };
    private int[] titles = {
            R.string.title_after_on_draw,
            R.string.title_before_on_draw,
            R.string.title_on_draw_layout,
            R.string.title_dispatch_draw,
            R.string.title_after_on_draw_foreground,
            R.string.title_before_on_draw_foreground
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.pager);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return DrawFragment.getInstance(layouts[i]);
            }

            @Override
            public int getCount() {
                return titles.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return getResources().getString(titles[position]);
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
