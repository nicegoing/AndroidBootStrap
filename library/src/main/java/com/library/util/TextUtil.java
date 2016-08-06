package com.library.util;

import android.text.TextUtils;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/6
 * @since 1.0
 */
public class TextUtil {

    /**
     * 对TextUtils.isEmpty进行封装，当服务器返回null时也作为empty处理
     *
     * @param str 字符串
     * @return true表示数据为"null"，null,0-length，false则相反
     */
    public static boolean isEmpty(CharSequence str) {
        return TextUtils.isEmpty(str) || "null".equals(str.toString().trim());
    }
}
