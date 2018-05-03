package jmm.baselibrary.injection.compoent;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import jmm.baselibrary.injection.module.AppModule;

/**
 * user:Administrator
 * time:2018 05 03 16:51
 * package_name:jmm.baselibrary.injection.compoent
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppCompoent {

    Context context();
}
