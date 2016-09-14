package com.androidbootstrap.ui.list;

import com.androidbootstrap.data.base.DataManager;
import com.androidbootstrap.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/14
 * @since 1.0
 */
public class TestListPresenter extends BasePresenter<ITestListView> {
    protected DataManager dataManager;

    @Inject
    public TestListPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }


}
