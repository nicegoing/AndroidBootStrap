package com.androidbootstrap.ui.login;

public class LoginPresenter extends BasePresenter<ILoginView> {
    protected DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
