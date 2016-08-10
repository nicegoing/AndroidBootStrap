package com.androidbootstrap.ui.widget.imageloader;

import android.graphics.Bitmap;

/**
 * 加载图片的回调
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/10
 * @since 1.0
 */
public interface LoaderListener {
    void onSuccess(Bitmap bitmap);

    void onError();
}
