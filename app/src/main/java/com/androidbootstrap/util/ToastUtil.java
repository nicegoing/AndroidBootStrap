package com.androidbootstrap.util;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.androidbootstrap.BaseApplication;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/8
 * @since 1.0
 */
public class ToastUtil {
    private static Toast toast;

    public static void show(@NonNull String message) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getContext(), message, Toast.LENGTH_SHORT);
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
