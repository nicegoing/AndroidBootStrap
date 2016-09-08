package com.androidbootstrap.data;

import com.androidbootstrap.constant.Constants;
import com.androidbootstrap.util.SpHelper;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/6
 * @since 1.0
 */
public class DataManagerImpl implements DataManager {
    SpHelper spHelper;

    public DataManagerImpl(SpHelper spHelper) {
        this.spHelper = spHelper;
    }

    @Override
    public SpHelper getSharePreferences() {
        return spHelper;
    }

    @Override
    public void writeEmail(String email) {
        getSharePreferences().save(Constants.SP_KEY.EMAIL, email);
    }

    @Override
    public String readEmail() {
        return getSharePreferences().getString(Constants.SP_KEY.EMAIL);
    }
}
