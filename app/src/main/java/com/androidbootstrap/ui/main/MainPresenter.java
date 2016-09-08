package com.androidbootstrap.ui.main;

import com.androidbootstrap.ui.base.BasePresenter;

import javax.inject.Inject;

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
}
