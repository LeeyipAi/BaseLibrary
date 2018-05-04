package com.jmm.fx.usercenter.injection.compoent;

import com.jmm.fx.usercenter.injection.modules.UserModule;
import com.jmm.fx.ui.activity.MainActivity;

import dagger.Component;
import jmm.baselibrary.injection.PreCompoentScope;
import jmm.baselibrary.injection.compoent.ActivityCompoent;

/**
 * user:HBKJ
 * time:2018 05 03 20:59
 * package_name:com.jmm.fx.usercenter.injection.compoent
 */
@PreCompoentScope
@Component(dependencies = ActivityCompoent.class,modules = UserModule.class)

public interface UserCompoent {

    void inject(MainActivity activity);
}
