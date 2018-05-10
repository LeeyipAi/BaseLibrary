package com.jmm.fx.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.jmm.fx.ui.fragment.NoticFragment;
import com.jmm.fx.ui.fragment.OrderFragment;
import com.jmm.fx.ui.fragment.ProductsFragment;
import com.jmm.fx.ui.fragment.UserFragment;

import jmm.baselibrary.ui.adapter.BaseFragmentVpAdapter;
import jmm.baselibrary.ui.fragment.BaseFragment;

/**
 * user:Administrator
 * time:2018 05 09 10:13
 * package_name:com.jmm.fx.ui.adapter
 */
public class MainVpAdapter extends BaseFragmentVpAdapter {

    public MainVpAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment =new ProductsFragment();
                break;
            case 1:
                fragment =new NoticFragment();
                break;
            case 2:
                fragment =new OrderFragment();
                break;
            case 3:
                fragment =new UserFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    protected void initFragment(BaseFragment fragment, int position) {
        Bundle data = new Bundle();
        data.putString("title", "第" + position + "个");
        fragment.setArguments(data);
    }

    @Override
    protected int getFragmentCount() {
        return 4;
    }
}
