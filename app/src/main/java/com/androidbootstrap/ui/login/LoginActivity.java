package com.androidbootstrap.ui.login;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setActivityComponent(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

}
