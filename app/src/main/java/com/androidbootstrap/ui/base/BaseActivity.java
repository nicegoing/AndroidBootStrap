package com.androidbootstrap.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androidbootstrap.inject.component.ActivityComponent;
import com.androidbootstrap.inject.component.AppComponent;
import com.androidbootstrap.inject.component.DaggerActivityComponent;
import com.library.ui.base.IView;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/6
 * @since 1.0
 */
public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity implements IView {
    @Inject
    protected T                     presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityComponent activityComponent = DaggerActivityComponent.builder().appComponent(AppComponent.Instance.get()).build();
        setActivityComponent(activityComponent);
        setContentView(getLayoutId());//抽取出来设置LayoutId
        ButterKnife.bind(this);
        if (presenter != null) {
            presenter.attachView(this);
        }
        initView(savedInstanceState);
        initData(savedInstanceState);
        initEvent(savedInstanceState);
    }

    protected abstract void setActivityComponent(ActivityComponent activityComponent);

    protected abstract
    @LayoutRes
    int getLayoutId();

    protected void initView(@Nullable Bundle savedInstanceState) {
    }

    protected void initData(@Nullable Bundle savedInstanceState) {
    }

    protected void initEvent(@Nullable Bundle savedInstanceState) {
    }


    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }


}
