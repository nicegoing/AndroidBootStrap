package com.androidbootstrap.util;

/**
 * 该类用于给方法传递泛型T的构造方法
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/18
 * @since 1.0
 */
public abstract class ClassFactory<T> {
    public Class<T> clazz;

    public abstract T create();

    public Class<T> getClazz() {
        return clazz;
    }
}
