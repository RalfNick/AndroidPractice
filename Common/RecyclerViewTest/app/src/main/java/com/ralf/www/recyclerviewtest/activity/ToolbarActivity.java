package com.ralf.www.recyclerviewtest.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.ralf.www.recyclerviewtest.R;

/**
 * @author Ralf
 * DESCRIPTION
 * @name ToolbarActivity
 * @date 2018/09/18 下午11:50
 **/
public abstract class ToolbarActivity extends AppCompatActivity {

    abstract protected int getLayoutResource();

    protected abstract int getMenuResource();

    public void onToolbarClick() {
    }

    protected void setMenuItemClickListener(Toolbar.OnMenuItemClickListener listener) {
        mToolbar.setOnMenuItemClickListener(listener);
    }

    protected AppBarLayout mAppBar;
    protected Toolbar mToolbar;
    protected TabLayout mTabLayout;
    protected boolean isHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        mAppBar = (AppBarLayout) findViewById(R.id.appbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        if (mToolbar == null || mAppBar == null) {
            throw new IllegalStateException("no toolbar");
        }

        mToolbar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onToolbarClick();
                    }
                }
        );

//        setSupportActionBar(mToolbar);

        if (Build.VERSION.SDK_INT >= 21) {
            mAppBar.setElevation(10.6f);
        }

        mToolbar.setNavigationIcon(R.mipmap.ic_home);//设置导航栏图标
//        mToolbar.setLogo(R.mipmap.ic_launcher);//设置app logo

        mToolbar.setTitle("妹纸");//设置主标题
        mToolbar.setTitleMarginStart(0);
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));//设置主标题颜色
        mToolbar.setTitleTextAppearance(this, R.style.Theme_ToolBar_Base_Title);//修改主标题的外观，包括文字颜色，文字大小等

//        mToolbar.setSubtitle("Subtitle");//设置子标题
//        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.darker_gray));//设置子标题颜色
//        mToolbar.setSubtitleTextAppearance(this, R.style.Theme_ToolBar_Base_Subtitle);//设置子标题的外观，包括文字颜色，文字大小等

        // 不能设置 setSupportActionBar(mToolbar);
        mToolbar.inflateMenu(getMenuResource());//设置右上角的填充菜单


    }

    // setSupportActionBar(mToolbar); 使用此方法设置menu ，会显示默认的 title
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //写一个menu的资源文件.然后创建就行了.
//        getMenuInflater().inflate(getMenuResource(),menu);
//        return super.onCreateOptionsMenu(menu);
//    }

    protected void setAppBarAlpha(float alpha) {
        mAppBar.setAlpha(alpha);
    }

    protected void hideOrShowToolbar() {
        mAppBar.animate()
                .translationY(isHidden ? 0 : -mAppBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();

        isHidden = !isHidden;
    }

}
