package com.jmm.fx.ui.fragment;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jmm.fx.test.IntegralDetails;
import com.jmm.fx.ui.adapter.TestAdapter;
import com.jmm.fx.usercenter.data.api.UserApi;

import java.util.List;

import jmm.baselibrary.bean.LoadStatus;
import jmm.baselibrary.data.net.HttpParams;
import jmm.baselibrary.data.net.RetrofitFactory;
import jmm.baselibrary.ui.adapter.BaseRvAdapter;
import jmm.baselibrary.ui.fragment.BaseRvFragment;
import jmm.baselibrary.utils.RxUtils;
import rx.Observable;

/**
 * user:Administrator
 * time:2018 05 04 10:55
 * package_name:com.jmm.fx.ui.fragment
 */

/*
    个人中心Fragment
 */
public class UserFragment extends BaseRvFragment<IntegralDetails.Data.Entity> {


    private String pageCount;

    @Override
    protected String getCurrentPage() {
        return pageCount;
    }

    @Override
    protected Observable<List<IntegralDetails.Data.Entity>> getApi(String currNum) {
        return  RetrofitFactory.getInstance()
                .creat(UserApi.class)
                .getIntegralDetail(HttpParams.getJf("2670196097418240", currNum, String.valueOf(PAGE_SIZE)))
                .compose(RxUtils.rxSchedulerHelper())
                .doOnNext(integralDetails -> pageCount = integralDetails.getData().getPager().getTotalPage())
                .map(integralDetails -> integralDetails.getData().getList());
    }

    @Override
    protected BaseRvAdapter getRecyclerViewAdapter() {
        return new TestAdapter();
    }

    @Override
    protected void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_user;
//    }
//
//    @Override
//    protected void initView() {
//
//    }
}
