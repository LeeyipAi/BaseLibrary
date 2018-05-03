package jmm.baselibrary.common;

import android.app.Application;
import android.content.Context;

import jmm.baselibrary.injection.compoent.AppCompoent;
import jmm.baselibrary.injection.compoent.DaggerAppCompoent;
import jmm.baselibrary.injection.module.AppModule;

/**
 * user:Administrator
 * time:2018 05 03 14:52
 * package_name:jmm.baselibrary.common
 */
public class BaseApplication extends Application{

    private static BaseApplication mBaseApplication;
    public AppCompoent mAppCompoent;


    @Override
    public void onCreate() {
        super.onCreate();
        mBaseApplication = this;
        initAppInjection();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    private void initAppInjection() {
        mAppCompoent = DaggerAppCompoent.builder().appModule(new AppModule(this)).build();
    }


    public static BaseApplication getBaseApplication() {
        return mBaseApplication;
    }
}
