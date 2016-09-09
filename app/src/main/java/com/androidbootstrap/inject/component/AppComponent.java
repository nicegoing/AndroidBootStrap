package com.androidbootstrap.inject.component;

import android.support.annotation.NonNull;

import com.androidbootstrap.data.base.DataManager;
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
    DataManager getDataManager();


     class Instance {
        private static AppComponent sComponent;

        public static void init(@NonNull AppComponent component) {
            sComponent = component;
        }

        public static AppComponent get() {
            return sComponent;
        }
    }
}
