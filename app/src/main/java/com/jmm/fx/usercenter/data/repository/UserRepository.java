package com.jmm.fx.usercenter.data.repository;

import com.jmm.fx.usercenter.data.api.UserApi;
import com.jmm.fx.usercenter.data.procotol.RegisterReq;

import javax.inject.Inject;

import jmm.baselibrary.data.net.RetrofitFactory;
import jmm.baselibrary.data.procotol.BaseResp;
import rx.Observable;

/**
 * user:HBKJ
 * time:2018 05 03 20:49
 * package_name:com.jmm.fx.usercenter.data.repository
 */
public class UserRepository {

    @Inject
    public UserRepository() {
    }

    public Observable<BaseResp<String>> register(String mobile, String verifyCode, String pwd){
        return RetrofitFactory.getInstance().creat(UserApi.class).register(new RegisterReq(mobile, verifyCode, pwd));
    }
}
