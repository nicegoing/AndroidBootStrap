package com.androidbootstrap.rx;

import com.androidbootstrap.rx.error.DefaultErrorBundle;
import com.androidbootstrap.rx.error.ErrorHanding;

import rx.Subscriber;

public abstract class RxSubscriber<T> extends Subscriber<T> {


    @Override
    public void onCompleted() {

    }

    @Override
    public void onNext(T t) {
        _noNext(t);
    }

    @Override
    public void onError(Throwable e) {
        _onError(ErrorHanding.handleError(new DefaultErrorBundle((Exception) e)));
    }

    /**
     * 正确返回数据在这里处理
     *
     * @param t 返回的数据
     */
    public abstract void _noNext(T t);

    /**
     * 错误统一在该方法中处理
     *
     * @param msg 错误类型
     */
    public abstract void _onError(String msg);
}
