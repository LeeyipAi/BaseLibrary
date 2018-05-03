package jmm.baselibrary.injection.module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * user:Administrator
 * time:2018 05 03 17:10
 * package_name:jmm.baselibrary.injection.module
 */
@Module
public class ActivityModule {

    Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    public Activity providerActivity(){
        return mActivity;
    }
}
