package com.androidbootstrap.ui.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.androidbootstrap.inject.component.ActivityComponent;
import com.androidbootstrap.base.activity.BaseListActivity;

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

    @Override
    protected RecyclerView.ItemDecoration getItemDecoration() {
        return null;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        super.initView(savedInstanceState);

//        showFragment(R.id.nav_view, new ClassFactory<TestFragment>(TestFragment.class) {
//            @Override
//            public TestFragment create() {
//                return new TestFragment();
//            }
//        });
    }
}
