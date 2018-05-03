package com.jmm.fx.usercenter.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.jmm.fx.R;
import com.jmm.fx.usercenter.injection.compoent.DaggerUserCompoent;
import com.jmm.fx.usercenter.presenter.RegisterPresenter;
import com.jmm.fx.usercenter.presenter.view.RegisterView;

import jmm.baselibrary.ui.activity.BaseMvpActivity;
import jmm.baselibrary.utils.ToastUtils;

public class MainActivity extends BaseMvpActivity<RegisterPresenter> implements RegisterView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View viewById = findViewById(R.id.text);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.register("12345678910","123456","123456");
            }
        });
    }

    @Override
    protected void injectComponent() {
        DaggerUserCompoent.builder().activityCompoent(mActivityCompoent).build().inject(this);
        mPresenter.mView = this;
    }

    @Override
    public void onRegisterResult(boolean result) {
        if (result) {
            ToastUtils.showShort("注册成功");
        }else {
            ToastUtils.showShort("注册失败");
        };
    }
}
