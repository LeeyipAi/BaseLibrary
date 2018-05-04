package com.jmm.fx.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jmm.fx.R;

import jmm.baselibrary.ui.fragment.BaseFragment;

/**
 * user:Administrator
 * time:2018 05 04 10:55
 * package_name:com.jmm.fx.ui.fragment
 */

/*
    商品库Fragment
 */
public class ProductsFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
