package com.androidbootstrap.util;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/8
 * @since 1.0
 */
public class ScreenUtil {

    public static int getScreenWidth() {
        return ResourceUtil.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return ResourceUtil.getResources().getDisplayMetrics().heightPixels;
    }

    public static float getScreenDensity() {
        return ResourceUtil.getResources().getDisplayMetrics().density;
    }

    /**
     * dp转换成px
     *
     * @param dp 独立像素单位
     * @return dp对应的像素
     */
    public static int dp2px(int dp) {
        return (int) (dp * getScreenDensity() + .5f);
    }

    /**
     * px转换成dp
     *
     * @param px 像素单位
     * @return 像素px对应的独立像素dp
     */
    public static int px2dp(int px) {
        return (int) (px / getScreenDensity() + .5f);
    }

}
