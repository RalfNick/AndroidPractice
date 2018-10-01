package com.ralf.www.recyclerviewtest.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ralf.www.recyclerviewtest.R;
import com.ralf.www.recyclerviewtest.adapter.DividerGridItemDecoration;
import com.ralf.www.recyclerviewtest.adapter.PictureAdapter;
import com.ralf.www.recyclerviewtest.entity.GanHuo;
import com.ralf.www.recyclerviewtest.net.BaseEntity;
import com.ralf.www.recyclerviewtest.net.NetApi;
import com.ralf.www.recyclerviewtest.net.RequestDataService;
import com.ralf.www.recyclerviewtest.net.RetrofitClient;
import com.ralf.www.recyclerviewtest.widget.MultiSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class MainActivity extends SwipeRefreshBaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.rv_meizhi)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    MultiSwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.main_fab)
    FloatingActionButton mainFab;

    private PictureAdapter mAdapter;
    private List<GanHuo> mPicList = new ArrayList<>();
    private int mIndex = 1;
    private boolean mIsFirstTimeTouchBottom = true;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected int getMenuResource() {
        return R.menu.menu_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        requestDataRefresh();
    }

    private void requestData(int index) {
        setRefreshing(true);

        RetrofitClient.getRetrofitClientInstance()
                .requestNetForData(NetApi.BASE_URL, RequestDataService.class)
                .getGanHuoData("福利", 10, index)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DefaultObserver<BaseEntity<GanHuo>>() {
                    @Override
                    public void onNext(BaseEntity<GanHuo> ganHuo) {
                        if (ganHuo != null && !ganHuo.isError()) {
                            refreshRecyclerView(ganHuo.getResults());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        setRefreshing(false);
                    }

                    @Override
                    public void onComplete() {
                        setRefreshing(false);
                        if (mIndex == 1) {
                            mRecyclerView.smoothScrollToPosition(0);
                        }
                    }
                });
    }

    private void refreshRecyclerView(List<GanHuo> ganHuoList) {
        if (ganHuoList == null || ganHuoList.size() < 1) {
            return;
        }
        if (mIndex == 1) {
            mPicList.clear();
            mPicList.addAll(ganHuoList);
            mAdapter.notifyDataSetChanged();
        } else {
            mPicList.addAll(ganHuoList);
            mAdapter.notifyItemRangeInserted(mAdapter.getItemCount()
                    , ganHuoList.size());
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_meizhi);
        mAdapter = new PictureAdapter(this, mPicList);

        // 添加动画
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        // 开源库的动画1
        mRecyclerView.setItemAnimator(new SlideInLeftAnimator());
        // 开源库的动画2
//        SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
//        mRecyclerView.setItemAnimator(animator);

        // 添加分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(
//                this, DividerItemDecoration.VERTICAL));
        // 网格分割线
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        // 设置布局管理器
//        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
//                2, StaggeredGridLayoutManager.VERTICAL);

        final GridLayoutManager layoutManager = new GridLayoutManager(this, 2
                , GridLayoutManager.VERTICAL, false);

        // SpanSizeLookup
        // A helper class to provide the number of spans each item occupies.
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mRecyclerView.getAdapter().getItemViewType(position);
                // 实际中最好根据 item 的类型来判断
                if (position == 0 || position == 1) {
                    // 相当于 LinearLayout
                    return 2;
                }

                return 1;
            }
        });
//        final LinearLayoutManager layoutManager = new LinearLayoutManager(this,
//                LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        // 设置点击事件
        mAdapter.setClickListener(new PictureAdapter.ItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.Adapter adapter, View view, int position) {
                GanHuo ganHuo = mPicList.get(position);
                String who = ganHuo.getWho();
                String desc = ganHuo.getDesc();
                if (view.getId() == R.id.tv_title) {
                    Toast.makeText(MainActivity.this, "你点击了" + who, Toast.LENGTH_SHORT).show();
                } else if (view.getId() == R.id.iv_meizhi) {
                    Intent picIntnet = new Intent(MainActivity.this, PictureActivity.class);
                    picIntnet.putExtra("url", ganHuo.getUrl());
                    startActivity(picIntnet);
                }
            }

            @Override
            public void onItemLongClick(RecyclerView.Adapter adapter, View view, int position) {
                GanHuo ganHuo = mPicList.get(position);
                String who = ganHuo.getWho();
                String desc = ganHuo.getDesc();
                if (view.getId() == R.id.tv_title) {
                    Toast.makeText(MainActivity.this, "你长按了" + who, Toast.LENGTH_SHORT).show();
                } else if (view.getId() == R.id.iv_meizhi) {
                    Toast.makeText(MainActivity.this, "你长按了" + desc, Toast.LENGTH_SHORT).show();
                }
            }

        });

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView rv, int dx, int dy) {
                        if (!mSwipeRefreshLayout.isRefreshing() &&
                                layoutManager.findLastCompletelyVisibleItemPosition() >= mAdapter.getItemCount() - 1) {

                            if (mIndex < 5) {
                                mIndex += 1;
                                requestData(mIndex);
                            }
                        }
                        // 瀑布式布局需要用这个条件判断，因为瀑布布局底部可见的 item 一般多余 1 个
//                        if (!mSwipeRefreshLayout.isRefreshing() &&
//                                layoutManager.findLastCompletelyVisibleItemPositions(new int[2])[1]
//                                        >= mAdapter.getItemCount() - 2){
//                            mIndex += 1;
//                            requestData(mIndex);
//                        }
                    }
                }
        );

        // 设置 toolbar 的 menu 事件
        setMenuItemClickListener(listener);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onToolbarClick() {
        super.onToolbarClick();
        mRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void requestDataRefresh() {
        super.requestDataRefresh();

        mIndex = 1;
        requestData(mIndex);
    }

    private Toolbar.OnMenuItemClickListener listener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int menuItemId = item.getItemId();
            if (menuItemId == R.id.action_search) {
                mAdapter.insertTest(1);
                Toast.makeText(MainActivity.this, "添加一个妹纸", Toast.LENGTH_SHORT).show();

            } else if (menuItemId == R.id.action_notification) {
                mAdapter.removeTest(1);
                Toast.makeText(MainActivity.this, "删除一个妹纸", Toast.LENGTH_SHORT).show();

            } else if (menuItemId == R.id.action_item1) {
                Toast.makeText(MainActivity.this, "设置一", Toast.LENGTH_SHORT).show();

            } else if (menuItemId == R.id.action_item2) {
                Toast.makeText(MainActivity.this, "设置二", Toast.LENGTH_SHORT).show();

            }
            return true;
        }
    };

    @OnClick({R.id.main_fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_fab:
                requestDataRefresh();
                break;
        }
    }
}
