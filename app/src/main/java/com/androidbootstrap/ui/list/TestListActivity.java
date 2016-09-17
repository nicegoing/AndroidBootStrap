package com.androidbootstrap.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidbootstrap.inject.component.ActivityComponent;
import com.androidbootstrap.ui.base.BaseListActivity;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/17
 * @since 1.0
 */
public class TestListActivity extends BaseListActivity<TestListPresenter,TestListAdapter> implements ITestListView {


    @Override
    protected void setActivityComponent(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }


    @NonNull
    @Override
    protected TestListAdapter getAdapter() {
        return new TestListAdapter(this);
    }

    @Override
    protected void loadData(int loadType) {
        presenter.loadName(loadType);
    }

    @NonNull
    @Override
    protected RecyclerView.LayoutManager getLayoutManger() {
        return new LinearLayoutManager(this);
    }

    @NonNull
    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }
}
