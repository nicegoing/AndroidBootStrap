package com.androidbootstrap.rx;

import com.androidbootstrap.rx.error.DefaultErrorBundle;
import com.androidbootstrap.rx.error.ErrorHanding;
import com.androidbootstrap.ui.base.view.IStateView;
import com.library.constant.Constant;

import rx.Subscriber;

/**
 * Activity需要实现IStateView接口，在获取数据前显示加载框，获取数据后进度消失
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/13
 * @since 1.0
 */
public abstract class ProgressSubscriber<T> extends Subscriber<T> {

    private IStateView stateView;

    public ProgressSubscriber(IStateView stateView) {
        this.stateView = stateView;
    }

    @Override
    public void onStart() {
        super.onStart();
        stateView.displayDialog(Constant.DIALOG_TYPE.LOADING_DIALOG);
    }

    @Override
    public void onCompleted() {
        stateView.hideDialog(Constant.DIALOG_TYPE.LOADING_DIALOG);
    }

    @Override
    public void onNext(T t) {
        _noNext(t);
    }

    @Override
    public void onError(Throwable e) {
        stateView.hideDialog(Constant.DIALOG_TYPE.LOADING_DIALOG);
        _onError(ErrorHanding.handleError(new DefaultErrorBundle((Exception) e)));
    }

    public abstract void _noNext(T t);

    public abstract void _onError(String msg);
}