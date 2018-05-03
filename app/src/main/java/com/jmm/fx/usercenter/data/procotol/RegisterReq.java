package com.jmm.fx.usercenter.data.procotol;

/**
 * user:HBKJ
 * time:2018 05 03 20:18
 * package_name:com.jmm.fx.usercenter.data.procotol
 */
public class RegisterReq {

    private String mobile;
    private String verifyCode;
    private String pwd;

    public RegisterReq(String mobile, String verifyCode, String pwd) {
        this.mobile = mobile;
        this.verifyCode = verifyCode;
        this.pwd = pwd;
    }
}
