package com.androidbootstrap.data;

import com.androidbootstrap.util.SpHelper;

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
}