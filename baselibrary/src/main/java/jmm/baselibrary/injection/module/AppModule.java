package jmm.baselibrary.injection.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jmm.baselibrary.common.BaseApplication;

/**
 * user:Administrator
 * time:2018 05 03 16:52
 * package_name:jmm.baselibrary.injection.module
 */

@Module
public class AppModule {

    BaseApplication context;

    public AppModule(BaseApplication context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context providesContext(){
        return context;
    }
}
