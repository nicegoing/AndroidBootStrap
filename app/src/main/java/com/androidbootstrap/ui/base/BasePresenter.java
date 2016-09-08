package com.androidbootstrap.ui.base;

import com.androidbootstrap.data.DataManager;
import com.androidbootstrap.inject.component.AppComponent;
import com.library.ui.IBaseView;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */


public class BasePresenter<T extends IBaseView> implements IPresenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    protected DataManager dataManager;

    public BasePresenter() {
        dataManager = AppComponent.Instance.get().getDataManager();
    }


    @Override
    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public T getView() {
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached())
            throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }


}

