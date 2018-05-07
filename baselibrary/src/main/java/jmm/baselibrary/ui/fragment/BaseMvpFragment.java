package jmm.baselibrary.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import jmm.baselibrary.common.BaseApplication;
import jmm.baselibrary.injection.compoent.ActivityCompoent;
import jmm.baselibrary.injection.compoent.DaggerActivityCompoent;
import jmm.baselibrary.injection.module.ActivityModule;
import jmm.baselibrary.injection.module.LifecycleProviderModule;
import jmm.baselibrary.presenter.BasePresenter;
import jmm.baselibrary.presenter.view.BaseView;
import jmm.baselibrary.utils.ToastUtils;
import jmm.baselibrary.widgets.ProgressLoading;

/**
 * user:Administrator
 * time:2018 05 04 10:49
 * package_name:jmm.baselibrary.ui.fragment
 */
public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements BaseView {

    private ProgressLoading mProgressLoading;

    @Inject
    public T mPresenter;

    public ActivityCompoent mActivityCompoent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mProgressLoading = new ProgressLoading(getContext());
        initActivityInjection();
        injectComponent();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        loadData();
    }

    /**
     *   Dagger注册
     */
    protected abstract void injectComponent();

    /**
     *   加载数据
     */
    protected abstract void loadData();


    // 初始Activity Component
    private void initActivityInjection() {
        mActivityCompoent = DaggerActivityCompoent.builder()
                .appCompoent(((BaseApplication) getActivity().getApplication()).mAppCompoent)
                .activityModule(new ActivityModule(getActivity()))
                .lifecycleProviderModule(new LifecycleProviderModule(this))
                .build();
    }

    @Override
    public void showLoading() {
        mProgressLoading.showLoading();
    }

    @Override
    public void hideLoading() {
        mProgressLoading.dismissLoading();
    }

    @Override
    public void onError(String msg) {
        ToastUtils.showShort(msg);
    }
}
