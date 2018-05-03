package jmm.baselibrary.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

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
 * time:2018 05 03 15:50
 * package_name:jmm.baselibrary.ui.activity
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    private ProgressLoading mProgressLoading;

    @Inject
    public T mPresenter;

    public ActivityCompoent mActivityCompoent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressLoading = new ProgressLoading(this);
        initActivityInjection();
        injectComponent();
    }

    /**
     *   Dagger注册
     */
    protected abstract void injectComponent();


    // 初始Activity Component
    private void initActivityInjection() {
        mActivityCompoent = DaggerActivityCompoent.builder()
                .appCompoent(((BaseApplication) getApplication()).mAppCompoent)
                .activityModule(new ActivityModule(this))
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
