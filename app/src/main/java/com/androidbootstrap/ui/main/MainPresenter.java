package com.androidbootstrap.ui.main;

import com.androidbootstrap.bean.Person;
import com.androidbootstrap.data.base.DataManager;
import com.androidbootstrap.rx.ProgressSubscriber;
import com.androidbootstrap.rx.StateSubscriber;
import com.androidbootstrap.base.presenter.BasePresenter;
import com.androidbootstrap.util.LogUtil;
import com.androidbootstrap.util.ToastUtil;

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

    public void loadProfile() {
        Subscription s = dataManager.loadProfile().subscribe(new StateSubscriber<Person>(getView()) {
            @Override
            public void _noNext(Person person) {
                LogUtil.d(person.toString());
                getView().setProfile(person);
            }

            @Override
            public void _onError(String msg) {
                ToastUtil.show(msg);
            }
        });

        addSubscribe(s);
    }

    public void loadProfileWithProgress() {
        Subscription s = dataManager.loadProfile().subscribe(new ProgressSubscriber<Person>(getView()) {
            @Override
            public void _noNext(Person person) {
                LogUtil.d(person.toString());
                getView().setProfile(person);
            }

            @Override
            public void _onError(String msg) {
                ToastUtil.show(msg);
            }
        });

        addSubscribe(s);

    }
}
