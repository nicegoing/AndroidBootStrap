package com.androidbootstrap.inject.component;

import com.androidbootstrap.inject.scope.PerActivity;
import com.androidbootstrap.ui.list.TestListActivity;
import com.androidbootstrap.ui.main.MainActivity;

import dagger.Component;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/6
 * @since 1.0
 */
@PerActivity
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(TestListActivity testBaseListActivity);
}
