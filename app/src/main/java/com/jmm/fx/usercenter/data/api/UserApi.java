package com.jmm.fx.usercenter.data.api;

import com.jmm.fx.test.IntegralDetails;
import com.jmm.fx.usercenter.data.procotol.RegisterReq;

import java.util.Map;

import io.reactivex.Observable;
import jmm.baselibrary.data.procotol.BaseResp;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * user:HBKJ
 * time:2018 05 03 20:00
 * package_name:com.jmm.fx.usercenter.data.api
 */
public interface UserApi {

    @POST("userCenter/register")
    Observable<BaseResp<String>> register(@Body RegisterReq req);

    @FormUrlEncoded
    @POST("http://test.ucbgo.com/csh/accountManager/api/getIntegralDetail")
    Observable<IntegralDetails> getIntegralDetail(@FieldMap Map<String, String> params);
}
