package com.androidbootstrap.data;

import com.androidbootstrap.constant.Constants;
import com.androidbootstrap.data.bean.Person;
import com.androidbootstrap.data.retrofit.RetrofitService;
import com.androidbootstrap.util.SpHelper;

import retrofit2.Call;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/6
 * @since 1.0
 */
public class DataManagerImpl implements DataManager {
    SpHelper spHelper;
    RetrofitService retrofitService;
    public DataManagerImpl(SpHelper spHelper, RetrofitService retrofitService) {
        this.spHelper = spHelper;
        this.retrofitService=retrofitService;
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

    @Override
    public Call<Person> getProfile() {
        return retrofitService.getProfile();
    }
}
