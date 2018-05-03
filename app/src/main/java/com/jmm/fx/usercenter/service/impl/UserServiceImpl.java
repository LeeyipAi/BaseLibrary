package com.jmm.fx.usercenter.service.impl;

import com.jmm.fx.usercenter.data.repository.UserRepository;
import com.jmm.fx.usercenter.service.UserService;

import javax.inject.Inject;

import jmm.baselibrary.data.procotol.BaseResp;
import rx.Observable;
import rx.functions.Func1;

/**
 * user:HBKJ
 * time:2018 05 03 20:24
 * package_name:com.jmm.fx.usercenter.service.impl
 */
public class UserServiceImpl implements UserService {

    @Inject
    public UserServiceImpl() {
    }

    @Inject
    UserRepository mUserRepository;

    @Override
    public Observable<Boolean> register(String mobile, String verifyCode, String pwd) {
        return mUserRepository.register(mobile, verifyCode, pwd)
                .flatMap(new Func1<BaseResp<String>, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(BaseResp<String> stringBaseResp) {
                        if (!stringBaseResp.getStatus().equals("0")) {
                            return Observable.just(false);
                        }
                        return Observable.just(true);
                    }
                });
    }
}
