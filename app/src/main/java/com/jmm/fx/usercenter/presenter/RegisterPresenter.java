package com.jmm.fx.usercenter.presenter;

import com.jmm.fx.usercenter.presenter.view.RegisterView;
import com.jmm.fx.usercenter.service.UserService;

import javax.inject.Inject;

import jmm.baselibrary.presenter.BasePresenter;
import jmm.baselibrary.rx.BaseSubscriber;
import jmm.baselibrary.utils.RxUtils;

/**
 * user:HBKJ
 * time:2018 05 03 20:25
 * package_name:com.jmm.fx.usercenter.presenter
 */
public class RegisterPresenter extends BasePresenter<RegisterView> {

    @Inject
    public RegisterPresenter() {
    }

    @Inject
    UserService mUserService;

    public void register(String mobile, String verifyCode, String pwd){
        mUserService.register(mobile,verifyCode,pwd)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribe(new BaseSubscriber<Boolean>() {
                    @Override
                    public void onNext(Boolean b) {
                        mView.onRegisterResult(b);
                    }
                });
    }

}
