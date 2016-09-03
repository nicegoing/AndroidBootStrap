package com.androidbootstrap.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/8
 * @since 1.0
 */
public class ToastUtil {
    private static Toast   toast;
    private static Context context;

    public ToastUtil(Context context) {
        this.context = context;
    }

    public static void show(@NonNull String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.show();
        }
    }

    public static void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
