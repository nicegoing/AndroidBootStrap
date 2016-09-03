package com.androidbootstrap.inject.component;

import com.androidbootstrap.data.DataManager;
import com.androidbootstrap.inject.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/12
 * @since 1.0
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(DataManager dataManager);
}
