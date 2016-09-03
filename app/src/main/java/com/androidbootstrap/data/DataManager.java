package com.androidbootstrap.data;

import com.androidbootstrap.util.SpHelper;

import javax.inject.Inject;

/**
 * 该类用于管理所有的数据
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/3
 * @since 1.0
 */
public class DataManager {
    @Inject
    SpHelper spHelper;

    public SpHelper getSharePreferece() {
        return spHelper;
    }

}
