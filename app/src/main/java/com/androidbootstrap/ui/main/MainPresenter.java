package com.androidbootstrap.ui.main;

import android.util.Log;

import com.androidbootstrap.data.bean.Person;
import com.androidbootstrap.ui.base.BasePresenter;

import javax.inject.Inject;

import rx.Subscriber;
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
        Subscription subscription = dataManager.getProfile().subscribe(new Subscriber<Person>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("MainActiviy", e.getMessage());

            }

            @Override
            public void onNext(Person person) {
                getView().setProfile(person);
            }
        });
        addSubscribe(subscription);
    }
}
