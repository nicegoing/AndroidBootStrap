package com.androidbootstrap.data.base;

import com.androidbootstrap.data.bean.Person;
import com.androidbootstrap.rx.ApiResponse;
import com.androidbootstrap.util.SpHelper;

import rx.Observable;

/**
 * 该类用于管理所有的数据
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/3
 * @since 1.0
 */

public interface DataManager {

    SpHelper getSharePreferences();

    void writeEmail(String email);

    String readEmail();

    Observable<ApiResponse<Person>> getProfile();
}