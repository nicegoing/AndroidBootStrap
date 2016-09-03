package com.androidbootstrap.inject.component;

import com.androidbootstrap.inject.module.DataManagerModule;

import javax.inject.Singleton;

import dagger.Component;
import presenter.base.BasePresenter;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/3
 * @since 1.0
 */
@Singleton
@Component(modules = DataManagerModule.class)
public interface DataManagerComponent {
    void inject(BasePresenter basePresenter);
}
