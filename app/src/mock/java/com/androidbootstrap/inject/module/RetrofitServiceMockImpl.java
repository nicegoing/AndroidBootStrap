package com.androidbootstrap.inject.module;

import com.androidbootstrap.bean.Name;
import com.androidbootstrap.bean.Person;
import com.androidbootstrap.data.retrofit.RetrofitService;
import com.androidbootstrap.rx.ApiResponse;
import com.androidbootstrap.rx.ListResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import rx.Observable;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/21
 * @since 1.0
 */

public class RetrofitServiceMockImpl implements RetrofitService {
    static Gson gson;

    public RetrofitServiceMockImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Observable<ApiResponse<Person>> loadProfile() {
        String json = "{\n" +
                "  \"code\": \"200\",\n" +
                "  \"msg\":\"success\",\n" +
                "  \"result\": {\n" +
                "    \"name\": \"Piasy\",\n" +
                "    \"gender\": 1,\n" +
                "    \"age\": 23,\n" +
                "    \"address\": {\n" +
                "      \"street-name\": \"创业路\",\n" +
                "      \"city\": \"深圳\"\n" +
                "    }\n" +
                "  }\n" +
                "}";
//        return getResp(json);
        return Observable.just((ApiResponse<Person>)gson.fromJson(json, new TypeToken<ApiResponse<Person>>() {
        }.getType()));
    }

    @Override
    public Observable<ApiResponse<ListResult<List<Name>>>> loadName() {
        return null;
    }

    public static <T> Observable<ApiResponse<T>> getResp(String str) {
        ApiResponse<T> resp = gson.fromJson(str, new TypeToken<ApiResponse<T>>() {
        }.getType());
        return Observable.just(resp);
    }
}
