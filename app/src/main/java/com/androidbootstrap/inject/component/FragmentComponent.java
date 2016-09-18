package com.androidbootstrap.inject.component;

import com.androidbootstrap.inject.scope.PerFragment;

import dagger.Component;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/18
 * @since 1.0
 */
@PerFragment
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {
}
