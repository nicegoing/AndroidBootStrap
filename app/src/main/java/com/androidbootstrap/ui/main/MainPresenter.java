package com.androidbootstrap.ui.main;

import com.androidbootstrap.bean.Person;
import com.androidbootstrap.data.base.DataManager;
import com.androidbootstrap.rx.RxSubscriber;
import com.androidbootstrap.ui.base.BasePresenter;
import com.androidbootstrap.util.LogUtil;

import javax.inject.Inject;

import rx.Subscription;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/3
 * @since 1.0
 */
public class MainPresenter extends BasePresenter<IMainView> {
    protected DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    void writeEmail(String email) {
        dataManager.writeEmail(email);
    }

    public String readEmail() {
        return dataManager.readEmail();
    }

    public void getProfile() {
        Subscription s = dataManager.getProfile().subscribe(new RxSubscriber<Person>() {
            @Override
            public void _noNext(Person person) {
                LogUtil.d(person.toString());
                getView().setProfile(person);
            }

            @Override
            public void _onError(String msg) {
                LogUtil.d(msg);
            }
        });
        addSubscribe(s);
    }
}
