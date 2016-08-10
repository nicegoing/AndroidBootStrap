package com.androidbootstrap.ui.widget.imageloader;

import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/10
 * @since 1.0
 */
public class ImageLoaderConfig {
    public static final int CENTER_CROP   = 0;
    public static final int FIT_CENTER    = 1;
    public static final int CENTER_INSIDE = 2;
    private int          placeHolderResId; //默认占位资源
    private int          errorResId; //错误时显示的资源
    private boolean      crossFade; //是否淡入淡出动画
    private int          crossDuration; //淡入淡出动画持续的时间
    private OverrideSize size; //图片最终显示在ImageView上的宽高度像素
    private int CropType = CENTER_CROP; //裁剪类型,默认为中部裁剪
    private boolean      asGif; //true,强制显示的是gif动画,如果url不是gif的资源,那么会回调error()
    private boolean      asBitmap;//true,强制显示为常规图片,如果是gif资源,则加载第一帧作为图片
    private boolean      skipMemoryCache;//true,跳过内存缓存,默认false
    private DiskCache    diskCacheStrategy; //硬盘缓存,默认为all类型
    private LoadPriority priority;

    public int getPlaceHolderResId() {
        return placeHolderResId;
    }

    public int getErrorResId() {
        return errorResId;
    }

    public boolean isCrossFade() {
        return crossFade;
    }

    public int getCrossDuration() {
        return crossDuration;
    }

    public OverrideSize getSize() {
        return size;
    }

    public int getCropType() {
        return CropType;
    }

    public boolean isAsGif() {
        return asGif;
    }

    public boolean isAsBitmap() {
        return asBitmap;
    }

    public boolean isSkipMemoryCache() {
        return skipMemoryCache;
    }

    public DiskCache getDiskCacheStrategy() {
        return diskCacheStrategy;
    }

    public LoadPriority getPriority() {
        return priority;
    }


    /**
     * 硬盘缓存策略
     */
    public enum DiskCache {
        NONE(DiskCacheStrategy.NONE),//跳过硬盘缓存
        SOURCE(DiskCacheStrategy.SOURCE),//仅仅保存原始分辨率的图片
        RESULT(DiskCacheStrategy.RESULT),//仅仅缓存最终分辨率的图像(降低分辨率后的或者转换后的)
        ALL(DiskCacheStrategy.ALL);//缓存所有版本的图片,默认行为
        private DiskCacheStrategy strategy;

        DiskCache(DiskCacheStrategy strategy) {
            this.strategy = strategy;
        }

        public DiskCacheStrategy getStrategy() {
            return strategy;
        }
    }

    /**
     * 加载优先级策略
     */
    public enum LoadPriority {
        LOW(Priority.LOW),
        NORMAL(Priority.NORMAL),
        HIGH(Priority.HIGH),
        IMMEDIATE(Priority.IMMEDIATE),;
        Priority priority;

        LoadPriority(Priority priority) {
            this.priority = priority;
        }

        public Priority getPriority() {
            return priority;
        }
    }

    private ImageLoaderConfig(ImageLoaderConfig.Builder builder) {
        this.placeHolderResId = builder.placeHolderResId;
        this.errorResId = builder.errorResId;
        this.crossFade = builder.crossFade;
        this.crossDuration = builder.crossDuration;
        this.size = builder.size;
        this.CropType = builder.CropType;
        this.asGif = builder.asGif;
        this.asBitmap = builder.asBitmap;
        this.skipMemoryCache = builder.skipMemoryCache;
        this.diskCacheStrategy = builder.diskCacheStrategy;
    }


    /**
     * Builder类
     */
    public static class Builder {
        private int          placeHolderResId;
        private int          errorResId;
        private boolean      crossFade;
        private int          crossDuration;
        private OverrideSize size;
        private int CropType = CENTER_CROP;
        private boolean asGif;
        private boolean asBitmap;
        private boolean skipMemoryCache;
        private DiskCache    diskCacheStrategy = DiskCache.ALL;
        private LoadPriority prioriy           = LoadPriority.HIGH;

        public Builder setPlaceHolderResId(Integer placeHolderResId) {
            this.placeHolderResId = placeHolderResId;
            return this;
        }

        public Builder setErrorResId(Integer errorResId) {
            this.errorResId = errorResId;
            return this;
        }

        public Builder setCrossFade(boolean crossFade) {
            this.crossFade = crossFade;
            return this;
        }

        public Builder setCrossDuration(int crossDuration) {
            this.crossDuration = crossDuration;
            return this;
        }

        public Builder setSize(OverrideSize size) {
            this.size = size;
            return this;
        }

        public Builder setCropType(int cropType) {
            CropType = cropType;
            return this;
        }

        public Builder setAsGif(boolean asGif) {
            this.asGif = asGif;
            return this;
        }

        public Builder setAsBitmap(boolean asBitmap) {
            this.asBitmap = asBitmap;
            return this;
        }

        public Builder setSkipMemoryCache(boolean skipMemoryCache) {
            this.skipMemoryCache = skipMemoryCache;
            return this;
        }

        public Builder setDiskCacheStrategy(DiskCache diskCacheStrategy) {
            this.diskCacheStrategy = diskCacheStrategy;
            return this;
        }

        public Builder setPrioriy(LoadPriority prioriy) {
            this.prioriy = prioriy;
            return this;
        }

        public ImageLoaderConfig build() {
            return new ImageLoaderConfig(this);
        }
    }

    /**
     * 图片最终显示在ImageView上的宽高像素
     * Created by mChenys on 2016/4/29.
     */
    public static class OverrideSize {
        private final int width;
        private final int height;

        public OverrideSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }


}
