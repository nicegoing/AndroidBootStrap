package com.androidbootstrap.ui.widget.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.androidbootstrap.R;
import com.androidbootstrap.util.LogUtil;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.GifRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/10
 * @since 1.0
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {

    //默认配置(此处请勿修改setDiskCacheStrategy，使用SOURCE可以避免图片底部显示兰绿色，如需求需要，请自定义ImageLoadConfig)
    public final static ImageLoaderConfig defaultConfig = new ImageLoaderConfig.Builder().
            setCropType(ImageLoaderConfig.CENTER_INSIDE).
            setAsBitmap(true).
            setPlaceHolderResId(R.mipmap.ic_launcher).
            setErrorResId(R.mipmap.ic_launcher).
            setDiskCacheStrategy(ImageLoaderConfig.DiskCache.SOURCE).
            setPrioriy(ImageLoaderConfig.LoadPriority.HIGH).build();

    private GlideImageLoaderStrategy() {

    }

    public static GlideImageLoaderStrategy getInstance() {
        return LazyHolder.strategy;
    }

    private static class LazyHolder {
        public static final GlideImageLoaderStrategy strategy = new GlideImageLoaderStrategy();
    }

    @Override
    public void loadImage(ImageView imageView, String imageUrl, ImageLoaderConfig config, LoaderListener listener) {
        Context context = imageView.getContext();
        if (null == imageUrl) {
            LogUtil.d("imageUrl is null");
        }
        if (null == config) {
            config = defaultConfig;
        }
        try {
            GenericRequestBuilder builder = null;
            if (config.isAsGif()) {//gif类型
                GifRequestBuilder request = Glide.with(context).load(imageUrl).asGif();
                if (config.getCropType() == ImageLoaderConfig.CENTER_CROP) {
                    request.centerCrop();
                } else {
                    request.fitCenter();
                }
                builder = request;
            } else if (config.isAsBitmap()) {  //bitmap 类型
                BitmapRequestBuilder request = Glide.with(context).load(imageUrl).asBitmap();
                if (config.getCropType() == ImageLoaderConfig.CENTER_CROP) {
                    request.centerCrop();
                } else if (config.getCropType() == ImageLoaderConfig.CENTER_INSIDE) {//实现centerInside效果(imageview scaletype设置后，此处要使用request.dontTransform设置才生效)
                    request.dontTransform();
                } else {
                    request.fitCenter();
                }
                builder = request;
            } else if (config.isCrossFade()) { // 渐入渐出动画
                DrawableRequestBuilder request = Glide.with(context).load(imageUrl).crossFade();
                if (config.getCropType() == ImageLoaderConfig.CENTER_CROP) {
                    request.centerCrop();
                } else {
                    request.fitCenter();
                }
                builder = request;
            }
            //缓存设置
            builder.diskCacheStrategy(config.getDiskCacheStrategy().getStrategy()).
                    skipMemoryCache(config.isSkipMemoryCache()).
                    priority(config.getPriority().getPriority());
            builder.dontAnimate();
            if (null != config.getSize()) {
                builder.override(config.getSize().getWidth(), config.getSize().getHeight());
            }

            if (null != listener) {
                setListener(builder, listener);
            }
            if (0 != config.getErrorResId()) {
                builder.error(config.getErrorResId());
            }
            if (0 != config.getPlaceHolderResId()) {
                builder.placeholder(config.getPlaceHolderResId());
            }
            if (null != config.getSize()) {
                builder.override(config.getSize().getWidth(), config.getSize().getHeight());
            }

        } catch (Exception e) {
            e.printStackTrace();
            if (imageView != null && config != null && config.getErrorResId() != 0) {
                imageView.setImageResource(config.getErrorResId());
            }
        }

    }

    private static void setListener(GenericRequestBuilder request, final LoaderListener listener) {
        request.listener(new RequestListener<Object, Bitmap>() {
            @Override
            public boolean onException(Exception e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                listener.onError();
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                listener.onSuccess(resource);
                LogUtil.d("setListener>>>>onException>>>>>");
                return false;
            }
        });
    }
}
