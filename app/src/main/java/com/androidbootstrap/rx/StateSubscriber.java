package com.androidbootstrap.rx;

import com.androidbootstrap.rx.error.DefaultErrorBundle;
import com.androidbootstrap.rx.error.ErrorHanding;
import com.androidbootstrap.base.view.IStateView;
import com.library.ui.view.MultiStateView;

import rx.Subscriber;

/**
 * Activity需要实现IStateView接口，实现自动转换状态
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/13
 * @since 1.0
 */
public abstract class StateSubscriber<T> extends Subscriber<T> {

    private IStateView stateView;

    public StateSubscriber(IStateView stateView) {
        this.stateView=stateView;
    }

    @Override
    public void onStart() {
        super.onStart();
        stateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onNext(T t) {
        if (RxUtil.isEmpty(t)) {
            stateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        } else {
            stateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
        }
        _noNext(t);
    }

    @Override
    public void onError(Throwable e) {
        stateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        _onError(ErrorHanding.handleError(new DefaultErrorBundle((Exception) e)));
    }

    public abstract void _noNext(T t);

    public abstract void _onError(String msg);
}