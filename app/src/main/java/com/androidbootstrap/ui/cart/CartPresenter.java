package com.androidbootstrap.ui.cart;

public class CartPresenter extends BasePresenter<ICartView> {
    protected DataManager dataManager;

    @Inject
    public CartPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
