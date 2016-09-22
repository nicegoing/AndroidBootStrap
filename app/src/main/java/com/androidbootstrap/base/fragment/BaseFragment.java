package com.androidbootstrap.base.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidbootstrap.R;
import com.androidbootstrap.inject.component.AppComponent;
import com.androidbootstrap.inject.component.DaggerFragmentComponent;
import com.androidbootstrap.inject.component.FragmentComponent;
import com.androidbootstrap.base.presenter.IPresenter;
import com.androidbootstrap.base.view.IView;
import com.androidbootstrap.util.ResourceUtil;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Fragment的基类
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/6
 * @since 1.0
 */
public abstract class BaseFragment<T extends IPresenter> extends Fragment implements IView {

    @Inject
    protected T presenter;

    /**
     * 基类保存Fragment显示和隐藏状态，解决在异常情况下Fragment重叠问题
     */
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this).commit();
            } else {
                ft.show(this).commit();
            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentComponent fragmentComponent = DaggerFragmentComponent.builder().appComponent(AppComponent.Instance.get()).build();
        setFragmentComponent(fragmentComponent);
        if (getLayoutId() <= 0) {
            throw new IllegalArgumentException(ResourceUtil.getString(R.string.no_layout_xml));
        }
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        if (presenter != null) {
            presenter.attachView(this);
        }
        initView(savedInstanceState);
        initData(savedInstanceState);
        initEvent(savedInstanceState);
        return view;
    }

    /**
     * 子类使用通过该方法注入Presenter
     *
     * @param fragmentComponent 管理fragment依赖注入的类
     */
    protected abstract void setFragmentComponent(FragmentComponent fragmentComponent);

    /**
     * 子类实现该方法设置layout资源文件
     *
     * @return layout资源文件的ID
     */
    protected abstract
    @LayoutRes
    int getLayoutId();

    protected void initView(@Nullable Bundle savedInstanceState) {
    }

    protected void initData(@Nullable Bundle savedInstanceState) {
    }

    protected void initEvent(@Nullable Bundle savedInstanceState) {
    }

    /**
     * 使用EventBus发送消息
     *
     * @param event 消息事件对象
     */
    public final <EVENT> void sendMessage(@NonNull EVENT event) {
        // 发布事件
        EventBus.getDefault().post(event);
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
