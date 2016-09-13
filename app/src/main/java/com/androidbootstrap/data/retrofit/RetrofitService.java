package com.androidbootstrap.data.retrofit;

import com.androidbootstrap.constant.Constants;
import com.androidbootstrap.bean.Person;
import com.androidbootstrap.rx.ApiResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/9
 * @since 1.0
 */
public interface RetrofitService {

    @GET(Constants.PERSON_URL)
    Observable<ApiResponse<Person>> loadProfile();
}
