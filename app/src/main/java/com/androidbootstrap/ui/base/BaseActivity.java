package com.androidbootstrap.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androidbootstrap.inject.component.ActivityComponent;
import com.androidbootstrap.inject.component.AppComponent;
import com.androidbootstrap.inject.component.DaggerActivityComponent;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/6
 * @since 1.0
 */
public abstract class BaseActivity extends AppCompatActivity {


    private   CompositeSubscription mCompositeSubscription;
    protected ActivityComponent     activityComponent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent = DaggerActivityComponent.builder().appComponent(AppComponent.Instance.get()).build();
        setContentView(getLayoutId());//抽取出来设置LayoutId
        initView(savedInstanceState);
        initData(savedInstanceState);
        initEvent(savedInstanceState);
    }

    protected abstract @LayoutRes int getLayoutId();

    protected void initView(@Nullable Bundle savedInstanceState) {
    }

    protected void initData(@Nullable Bundle savedInstanceState) {
    }

    protected void initEvent(@Nullable Bundle savedInstanceState) {
    }


    public CompositeSubscription getCompositeSubscription() {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        return this.mCompositeSubscription;
    }


    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }

        this.mCompositeSubscription.add(s);
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null) {
            this.mCompositeSubscription.unsubscribe();
        }
    }

}
