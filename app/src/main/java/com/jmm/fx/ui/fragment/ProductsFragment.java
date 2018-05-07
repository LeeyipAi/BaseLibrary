package com.jmm.fx.ui.fragment;

import android.widget.TextView;

import com.jmm.fx.R;

import butterknife.BindView;
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


    @BindView(R.id.tv_product)
    TextView mTvProduct;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product;
    }

    @Override
    protected void initView() {
        mTvProduct.setText("商品库");
    }

}
