package com.androidbootstrap.rx.error;

/**
 * 错误数据接口
 */
public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}
