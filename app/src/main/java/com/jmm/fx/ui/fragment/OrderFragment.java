package com.jmm.fx.ui.fragment;

import android.widget.TextView;

import com.jmm.fx.R;

import butterknife.BindView;
import jmm.baselibrary.rx.rxbus2.RxBus;
import jmm.baselibrary.rx.rxbus2.Subscribe;
import jmm.baselibrary.rx.rxbus2.ThreadMode;
import jmm.baselibrary.ui.fragment.BaseFragment;
import jmm.baselibrary.utils.ToastUtils;

/**
 * user:Administrator
 * time:2018 05 04 10:55
 * package_name:com.jmm.fx.ui.fragment
 */

/*
    订单Fragment
 */
public class OrderFragment extends BaseFragment {

    @BindView(R.id.text)
    TextView mText;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView() {
        mText.setOnClickListener(v -> RxBus.getDefault().post("发出一个消息"));
    }

    @Override
    protected void lazyFetchData() {
        ToastUtils.showShort("OrderFragment");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMsg(String s) {
        ToastUtils.showShort(s);
    }
}
