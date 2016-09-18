package com.androidbootstrap.ui.base.activity;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.androidbootstrap.R;
import com.androidbootstrap.inject.component.ActivityComponent;
import com.androidbootstrap.inject.component.AppComponent;
import com.androidbootstrap.inject.component.DaggerActivityComponent;
import com.androidbootstrap.ui.base.presenter.IPresenter;
import com.androidbootstrap.ui.base.view.IView;
import com.androidbootstrap.util.ClassFactory;
import com.androidbootstrap.util.ResourceUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

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
    protected T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityComponent activityComponent = DaggerActivityComponent.builder().appComponent(AppComponent.Instance.get()).build();
        setActivityComponent(activityComponent);
        if (getLayoutId() <= 0) {
            throw new IllegalArgumentException(ResourceUtil.getString(R.string.no_layout_xml));
        }
        setContentView(getLayoutId());//抽取出来设置LayoutId
        ButterKnife.bind(this);
        if (presenter != null) {
            presenter.attachView(this);
        }
        initView(savedInstanceState);
        initData(savedInstanceState);
        initEvent(savedInstanceState);
    }

    /**
     * 子类使用通过该方法注入Presenter
     *
     * @param activityComponent 管理Activity依赖注入的类
     */
    protected abstract void setActivityComponent(ActivityComponent activityComponent);

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
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    /**
     * 切换Fragment
     *
     * @param containerViewId
     * @param to              切换到的Fragment
     */
    protected void replaceFragment(@IdRes int containerViewId, @NonNull Fragment to) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerViewId, to)
                .commit();
    }

    /**
     * 获取对应的Fragment实例，MainActivity可以通过该方法找到对应Fragment
     * findFragmentByTag在某些情况会失效
     *
     * @param clazz 对应的Fragment字节码
     * @param <T>   对应Fragment实例
     * @return 如果存在则返回对应的实例，如果不存在，则返回null
     */
    protected <T extends Fragment> T getFragment(Class<T> clazz) {
        T result = null;
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (int i = 0; fragments != null && i < fragments.size(); i++) {
            if (fragments.get(i).getClass().equals(clazz)) {
                result = (T) fragments.get(i);
                break;
            }
        }
        return result;
    }

    /**
     * 通过add的方式显示指定的Fragment
     */
    public <T extends Fragment> void showFragment(@IdRes int containerViewId, ClassFactory<T> classFactory) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment result = getFragment(classFactory.getClazz());
        //如果集合中没有fragment，新建该Fragment,加入集合然后显示
        if (result == null) {
            transaction.add(containerViewId, classFactory.create(), classFactory.getClazz().getCanonicalName());
        }

        for (int i = 0; fragments != null && i < fragments.size(); i++) {
            if (!fragments.get(i).isHidden()) {
                transaction.hide(fragments.get(i));
            }
        }
        transaction.show(result).commit();
    }
}
