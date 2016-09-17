package com.androidbootstrap.ui.list;

import com.androidbootstrap.bean.Name;
import com.androidbootstrap.data.base.DataManager;
import com.androidbootstrap.rx.ListResult;
import com.androidbootstrap.rx.ListSubscriber;
import com.androidbootstrap.ui.base.BasePresenter;

import java.util.List;

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

    public void loadName(final int loadType){
        dataManager.loadName().subscribe(new ListSubscriber<List<Name>>(getView(),loadType) {
            @Override
            public void _noNext(ListResult<List<Name>> t) {
                getView().onSuccess(t.list(),loadType);
            }

            @Override
            public void _onError(String msg) {

            }
        });
    }

}
