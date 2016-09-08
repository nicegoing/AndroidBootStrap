package com.androidbootstrap.inject.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/6
 * @since 1.0
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
