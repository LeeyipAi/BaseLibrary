package com.jmm.fx.usercenter.injection.modules;

        import com.jmm.fx.usercenter.service.UserService;
        import com.jmm.fx.usercenter.service.impl.UserServiceImpl;

        import dagger.Module;
        import dagger.Provides;

/**
 * user:HBKJ
 * time:2018 05 03 20:57
 * package_name:com.jmm.fx.usercenter.injection.modules
 */

@Module
public class UserModule {

    @Provides
    UserService providesUserService(UserServiceImpl service){
        return service;
    }
}
