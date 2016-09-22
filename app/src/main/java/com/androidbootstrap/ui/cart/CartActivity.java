package com.androidbootstrap.ui.cart;

public class CartActivity extends BaseActivity<CartPresenter> implements ICartView {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_cart;
    }

    @Override
    protected void setActivityComponent(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

}
