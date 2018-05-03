package jmm.baselibrary.injection.compoent;

import android.app.Activity;
import android.content.Context;

import com.trello.rxlifecycle.LifecycleProvider;

import dagger.Component;
import jmm.baselibrary.injection.ActivityScope;
import jmm.baselibrary.injection.module.ActivityModule;
import jmm.baselibrary.injection.module.LifecycleProviderModule;

/**
 * user:Administrator
 * time:2018 05 03 17:09
 * package_name:jmm.baselibrary.injection.compoent
 */

@ActivityScope
@Component(dependencies = AppCompoent.class,modules = {ActivityModule.class,LifecycleProviderModule.class})
public interface ActivityCompoent {

    Activity activity();

    Context context();

    LifecycleProvider lifecycle();
}
