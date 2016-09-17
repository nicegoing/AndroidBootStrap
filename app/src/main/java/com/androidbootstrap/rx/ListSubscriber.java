package com.androidbootstrap.rx;

import com.androidbootstrap.constant.Constants;
import com.androidbootstrap.rx.error.DefaultErrorBundle;
import com.androidbootstrap.rx.error.ErrorHanding;
import com.androidbootstrap.ui.base.IListView;
import com.library.ui.view.MultiStateView;

import rx.Subscriber;

/**
 * 用于列表刷新，加载更多等视图处理的观察者（消费者）
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/14
 * @since 1.0
 */
public abstract class ListSubscriber<T> extends Subscriber<ListResult<T>> {

    private IListView iListView;
    private int       loadType;

    public ListSubscriber(IListView iListView, int loadType) {
        this.iListView = iListView;
        this.loadType = loadType;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (loadType == Constants.LoadType.FIRST_IN) {
            iListView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        }
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onNext(ListResult<T> t) {
        switch (loadType) {
            case Constants.LoadType.FIRST_IN:
                if (RxUtil.isEmpty(t.list())) {
                    iListView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
                } else {
                    iListView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                }
                break;
            case Constants.LoadType.REFRESH:
                iListView.setRefreshing(false);
                break;
            case Constants.LoadType.LOAD_MORE:
                iListView.setLoadMoreFinish(t.page() < t.pageCount());
                break;
        }
        _noNext(t);
    }

    @Override
    public void onError(Throwable e) {
        switch (loadType) {
            case Constants.LoadType.FIRST_IN:
                iListView.setViewState(MultiStateView.VIEW_STATE_ERROR);
                break;
            case Constants.LoadType.REFRESH:
                iListView.setRefreshing(false);
                break;
            case Constants.LoadType.LOAD_MORE:
                iListView.setLoadMoreFinish(true);
                break;
        }

        _onError(ErrorHanding.handleError(new DefaultErrorBundle((Exception) e)));
    }

    public abstract void _noNext(ListResult<T> t);

    public abstract void _onError(String msg);

}
