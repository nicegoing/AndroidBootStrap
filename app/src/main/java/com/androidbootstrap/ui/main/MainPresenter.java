package com.androidbootstrap.ui.main;

import android.util.Log;

import com.androidbootstrap.data.bean.Person;
import com.androidbootstrap.ui.base.BasePresenter;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/3
 * @since 1.0
 */
public class MainPresenter extends BasePresenter<IMainView> {
    @Inject
    public MainPresenter() {
    }

    void writeEmail(String email) {
        dataManager.writeEmail(email);
    }

    public String readEmail() {
        return dataManager.readEmail();
    }

    public void getProfile() {
        dataManager.getProfile().enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                getView().setProfile(response.body());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.i("MainActiviy", t.getMessage());
            }
        });
    }
}
