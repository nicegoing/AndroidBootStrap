package com.androidbootstrap.ui.widget.imageloader;

import android.widget.ImageView;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/10
 * @since 1.0
 */
public interface BaseImageLoaderStrategy {
    void loadImage(ImageView imageView, String imageUrl, ImageLoaderConfig imageLoaderConfig, LoaderListener listener);
}
