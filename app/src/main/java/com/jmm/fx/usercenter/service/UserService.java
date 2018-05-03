package com.jmm.fx.usercenter.service;

import rx.Observable;

/**
 * user:HBKJ
 * time:2018 05 03 20:23
 * package_name:com.jmm.fx.usercenter.service
 */
public interface UserService {

    //注册
    Observable<Boolean> register(String mobile, String verifyCode, String pwd);
}
