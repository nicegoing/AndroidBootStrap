package com.androidbootstrap.ui.main;

import android.util.Log;

import com.androidbootstrap.data.bean.Person;
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
    @Inject
    public MainPresenter() {
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
                LogUtil.i(person.toString());
                getView().setProfile(person);
            }

            @Override
            public void _onError(String msg) {
                Log.i("error", msg);
            }
        });
        addSubscribe(s);
    }
}
