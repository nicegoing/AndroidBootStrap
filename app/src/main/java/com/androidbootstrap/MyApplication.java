package com.androidbootstrap;

import com.androidbootstrap.inject.component.AppComponent;
import com.androidbootstrap.inject.component.DaggerAppComponent;
import com.androidbootstrap.inject.module.AppModule;
import com.androidbootstrap.util.NetUtil;
import com.androidbootstrap.util.ResourceUtil;
import com.androidbootstrap.util.ToastUtil;
import com.facebook.stetho.Stetho;

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
        init();
        AppComponent.Instance.init(DaggerAppComponent.builder().appModule(new AppModule(this)).build());
        Stetho.initializeWithDefaults(this);
    }

    private void init() {
        ToastUtil.init(this);
        ResourceUtil.init(this);
        NetUtil.init(this);
    }
}
