package com.androidbootstrap.ui.widget.imageloader;

import android.widget.ImageView;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/10
 * @since 1.0
 */
public class ImageLoader {
    public static void loadImage(ImageView imageView, String imageUrl, ImageLoaderConfig imageLoaderConfig, LoaderListener listener) {
        GlideImageLoaderStrategy.getInstance().loadImage(imageView, imageUrl, imageLoaderConfig, listener);
    }
}
