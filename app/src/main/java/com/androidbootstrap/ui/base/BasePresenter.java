package com.androidbootstrap.ui.base;

import com.androidbootstrap.data.base.DataManager;
import com.androidbootstrap.inject.component.AppComponent;
import com.library.ui.IView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */


public class BasePresenter<T extends IView> implements IPresenter<T> {

    private   T                     view;
    protected CompositeSubscription compositeSubscription;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    protected DataManager dataManager;

    public BasePresenter() {
        dataManager = AppComponent.Instance.get().getDataManager();
    }


    @Override
    public void detachView() {
        view = null;
        unSubscribe();
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public T getView() {
        return view;
    }

    public void checkViewAttached() {
        if (!isViewAttached())
            throw new ViewNotAttachedException();
    }

    public static class ViewNotAttachedException extends RuntimeException {
        public ViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }


    protected void unSubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }

    protected void addSubscribe(Subscription s) {
        if (this.compositeSubscription == null) {
            this.compositeSubscription = new CompositeSubscription();
        }
        this.compositeSubscription.add(s);
    }
}

