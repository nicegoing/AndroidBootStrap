package com.androidbootstrap.util;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/8
 * @since 1.0
 */
public class ResourceUtil {
    private static Context context;

    public static void init(Context context) {
        ResourceUtil.context = context;
    }


    public static Resources getResources() {
        return context.getResources();
    }

    public static String getString(@StringRes int resourceId) {
        return getResources().getString(resourceId);
    }

    public static int getColor(@ColorRes int colorId) {
        return ResourcesCompat.getColor(getResources(), colorId, null);
    }

    public static int getDimen(@DimenRes int resId) {
        return getResources().getDimensionPixelSize(resId);
    }
}
