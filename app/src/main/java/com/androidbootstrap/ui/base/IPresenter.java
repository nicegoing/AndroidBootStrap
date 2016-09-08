package com.androidbootstrap.ui.base;

import com.library.ui.IBaseView;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/3
 * @since 1.0
 */
public  interface IPresenter<V extends IBaseView> {

    void attachView(V iBaseView);

    void detachView();
}
