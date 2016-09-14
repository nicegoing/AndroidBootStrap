package com.androidbootstrap.ui.list;

import com.androidbootstrap.inject.component.ActivityComponent;
import com.androidbootstrap.ui.base.BaseActivity;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/14
 * @since 1.0
 */
public class TestListActivity extends BaseActivity<TestListPresenter> implements ITestListView {
    @Override
    protected void setActivityComponent(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void setViewState(int state) {

    }

    @Override
    public void displayDialog(int dialogType) {

    }

    @Override
    public void hideDialog(int... dialogType) {

    }
}
