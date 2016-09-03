package com.androidbootstrap.inject.component;

import android.content.Context;

import com.androidbootstrap.inject.module.AppModule;
import com.androidbootstrap.util.SpHelper;

import dagger.Component;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/12
 * @since 1.0
 */
@Component(modules = AppModule.class)
public interface AppComponent {

    Context getContenxt();

    SpHelper getSpHelper();
}
