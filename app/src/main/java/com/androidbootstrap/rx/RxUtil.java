package com.androidbootstrap.rx;

import java.util.Collection;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/13
 * @since 1.0
 */
public class RxUtil {
    /**
     * 如果object为null或者object为空集合时，把该数据当作空处理
     *
     * @param obj 进行判断的对象
     * @return true表示Object为空，false表示Object不为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
        return false;
    }

}
