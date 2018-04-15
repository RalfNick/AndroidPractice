package com.cdy.shoppingcart.shoppingcartdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdy.shoppingcart.shoppingcartdemo.R;

/**
 * Created by caobo on 2016/7/21 0021.
 * 小区商圈店铺评论列表
 */
public class BusinessCommentListFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater
                .inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
