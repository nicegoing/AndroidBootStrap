package com.androidbootstrap.inject.module;

import com.androidbootstrap.data.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/3
 * @since 1.0
 */
@Module
public class DataManagerModule {

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new DataManager();
    }
}
