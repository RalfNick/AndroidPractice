package com.cdy.shoppingcart.shoppingcartdemo;

import com.cdy.shoppingcart.shoppingcartdemo.fragment.BusinessCommentListFragment;
import com.cdy.shoppingcart.shoppingcartdemo.fragment.BusinessFragment;
import com.cdy.shoppingcart.shoppingcartdemo.fragment.ProductsFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by 曹博 on 2016/6/6.
 * 小区商家商家详情
 */
public class MerchantActivity extends FragmentActivity {
    private RadioGroup discount_layout;
    /**
     * 团优惠
     */
    private RadioButton group_rb;
    /**
     * 优惠活动
     */
    private RadioButton preferential_rb;
    /**
     * 优惠券
     */
    private RadioButton coupon_rb;
    /**
     * 下划线标记
     */
    private View group_line, preferential_line, coupon_line;
    /**
     * 服务产品
     */
    private ProductsFragment productsFragment;
    /**
     * 商家信息
     */
    private BusinessFragment businessFragment;
    /**
     * 商家评论
     */
    private BusinessCommentListFragment businessCommentListFragment;

    private ViewPager pager;

    //上门服务Fragment
    private ArrayList<Fragment> fragments;

    /**
     * 标题名集合
     */
    private RadioButton[] titleText = null;

//    private Commercial commercial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);
        initView();
    }

    private void initView() {
        pager = (ViewPager) findViewById(R.id.pager);
        discount_layout = (RadioGroup) findViewById(R.id.discount_layout);
        group_rb = (RadioButton) findViewById(R.id.group_rb);
        preferential_rb = (RadioButton) findViewById(R.id.preferential_rb);
        coupon_rb = (RadioButton) findViewById(R.id.coupon_rb);
        group_line = findViewById(R.id.group_line);
        preferential_line = findViewById(R.id.preferential_line);
        coupon_line = findViewById(R.id.coupon_line);

        titleText = new RadioButton[]{group_rb, preferential_rb, coupon_rb};
        discount_layout.setOnCheckedChangeListener(listener);

        fragments = new ArrayList<>();
        productsFragment = new ProductsFragment();
        businessFragment = new BusinessFragment();
        businessCommentListFragment = new BusinessCommentListFragment();
        fragments.add(productsFragment);
        fragments.add(businessFragment);
        fragments.add(businessCommentListFragment);

        MyFragmentPagerAdapter fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(fragmentPagerAdapter);
        fragmentPagerAdapter.setFragments(fragments);
        pager.setOnPageChangeListener(new MyOnPageChangeListener());
        // 第一次启动时选中第0个tab
        pager.setCurrentItem(0);
        pager.setOffscreenPageLimit(2);
    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.group_rb:
                    pager.setCurrentItem(0);
                    break;
                case R.id.preferential_rb:
                    pager.setCurrentItem(1);
                    break;
                case R.id.coupon_rb:
                    pager.setCurrentItem(2);
                    break;
            }
        }
    };


    /**
     * 切换更换下划线状态
     *
     * @param position
     */
    private void setVisible(int position) {
        switch (position) {
            case 0:
                group_line.setVisibility(View.VISIBLE);
                preferential_line.setVisibility(View.INVISIBLE);
                coupon_line.setVisibility(View.INVISIBLE);
                break;
            case 1:
                group_line.setVisibility(View.INVISIBLE);
                preferential_line.setVisibility(View.VISIBLE);
                coupon_line.setVisibility(View.INVISIBLE);
                break;
            case 2:
                group_line.setVisibility(View.INVISIBLE);
                preferential_line.setVisibility(View.INVISIBLE);
                coupon_line.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 设置选中图标的文字颜色与
     * 下划线可见
     *
     * @param index
     */
    private void chingeIndexView(int index) {
        for (int i = 0; i < titleText.length; i++) {
            titleText[i].setChecked(false);
        }
        if (index < titleText.length) {
            titleText[index].setChecked(true);
        }

    }


    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> fragments;
        private FragmentManager fm;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fm = fm;
            this.fragments = fragments;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }


        public void setFragments(ArrayList<Fragment> fragments) {
            if (this.fragments != null) {
                FragmentTransaction ft = fm.beginTransaction();
                for (Fragment f : this.fragments) {
                    ft.remove(f);
                }
                ft.commit();
                ft = null;
                fm.executePendingTransactions();
            }
            this.fragments = fragments;
            notifyDataSetChanged();
        }


        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            Object obj = super.instantiateItem(container, position);
            return obj;
        }

    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int position) {
            chingeIndexView(position);
            setVisible(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
        }
    }
}
