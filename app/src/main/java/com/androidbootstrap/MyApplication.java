package com.androidbootstrap;

import com.androidbootstrap.inject.component.DaggerAppComponent;
import com.androidbootstrap.inject.module.AppModule;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/7/18
 * @since 1.0
 */
public class MyApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }
}
