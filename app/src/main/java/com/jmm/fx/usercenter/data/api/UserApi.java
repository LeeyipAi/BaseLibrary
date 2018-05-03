package com.jmm.fx.usercenter.data.api;

import com.jmm.fx.usercenter.data.procotol.RegisterReq;

import jmm.baselibrary.data.procotol.BaseResp;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * user:HBKJ
 * time:2018 05 03 20:00
 * package_name:com.jmm.fx.usercenter.data.api
 */
public interface UserApi {

    @POST("userCenter/register")
    Observable<BaseResp<String>> register(@Body RegisterReq req);
}
