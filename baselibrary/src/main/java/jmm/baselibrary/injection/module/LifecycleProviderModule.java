package jmm.baselibrary.injection.module;

import com.trello.rxlifecycle.LifecycleProvider;

import dagger.Module;
import dagger.Provides;

/**
 * user:Administrator
 * time:2018 05 03 17:21
 * package_name:jmm.baselibrary.injection.module
 */

@Module
public class LifecycleProviderModule {
    LifecycleProvider mLifecycleProvider;

    public LifecycleProviderModule(LifecycleProvider lifecycleProvider) {
        mLifecycleProvider = lifecycleProvider;
    }

    @Provides
    public LifecycleProvider providerLifecycleProvider(){
        return mLifecycleProvider;
    }
}
