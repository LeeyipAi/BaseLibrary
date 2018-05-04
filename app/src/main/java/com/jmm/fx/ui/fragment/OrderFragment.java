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
    订单Fragment
 */
public class OrderFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_order,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
