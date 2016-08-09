package com.androidbootstrap;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/8
 * @since 1.0
 */
public class BaseApplication extends MultiDexApplication {
    protected static BaseApplication context;

    public static Context getContext() {
        return context.getApplicationContext();
    }
}
