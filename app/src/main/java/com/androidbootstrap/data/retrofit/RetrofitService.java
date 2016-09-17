package com.androidbootstrap.data.retrofit;

import com.androidbootstrap.bean.Name;
import com.androidbootstrap.bean.Person;
import com.androidbootstrap.constant.Constants;
import com.androidbootstrap.rx.ApiResponse;
import com.androidbootstrap.rx.ListResult;

import java.util.List;

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

    @GET(Constants.NAME_URL)
    Observable<ApiResponse<ListResult<List<Name>>>> loadName();

}
