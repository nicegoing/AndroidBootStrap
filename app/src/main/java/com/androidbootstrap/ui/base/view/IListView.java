package com.androidbootstrap.ui.base.view;

import java.util.List;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/14
 * @since 1.0
 */
public interface IListView extends IStateView {
    void setRefreshing(boolean refreshing);

    void setLoadMoreFinish(boolean hasMore);

    void onSuccess(List names, int loadType);
}
