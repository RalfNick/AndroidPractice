package com.ralf.www.recyclerviewtest.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import com.ralf.www.recyclerviewtest.R;
import com.ralf.www.recyclerviewtest.widget.MultiSwipeRefreshLayout;

/**
 * @author Ralf
 * DESCRIPTION
 * @name SwipeRefreshBaseActivity
 * @date 2018/09/18 下午11:51
 **/
public abstract class SwipeRefreshBaseActivity extends ToolbarActivity{

    MultiSwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        trySetupSwipeRefresh();
    }

    void trySetupSwipeRefresh() {
        mSwipeRefreshLayout = (MultiSwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setColorSchemeResources(
                    R.color.refresh_progress_3,
                    R.color.refresh_progress_2,
                    R.color.refresh_progress_1
            );
            mSwipeRefreshLayout.setOnRefreshListener(
                    new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            requestDataRefresh();
                        }
                    }
            );
        }
    }

    public void requestDataRefresh() {}

    public void setRefreshing(boolean refreshing) {
        if (mSwipeRefreshLayout == null) {
            return;
        }
        if (!refreshing) {
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mSwipeRefreshLayout.setRefreshing(true);
        }
    }

    public void setProgressViewOffset(boolean scale, int start, int end) {
        mSwipeRefreshLayout.setProgressViewOffset(scale, start, end);
    }

    public void setSwipeableChildren(
            MultiSwipeRefreshLayout.CanChildScrollUpCallback canChildScrollUpCallback) {
        mSwipeRefreshLayout.setCanChildScrollUpCallback(
                canChildScrollUpCallback
        );
    }

}
