package com.androidbootstrap.inject.module;

import android.content.Context;

import com.androidbootstrap.constant.Constants;
import com.androidbootstrap.data.DataManager;
import com.androidbootstrap.data.DataManagerImpl;
import com.androidbootstrap.data.base.AutoValueGson_MyAdapterFactory;
import com.androidbootstrap.data.retrofit.RetrofitService;
import com.androidbootstrap.util.SpHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/12
 * @since 1.0
 */
@Module
public class AppModule {
    protected final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }


    @Provides
    @Singleton
    SpHelper provideSpHelper(Context context) {
        return new SpHelper(context);
    }

    @Provides
    @Singleton
    DataManager provideDataManager(SpHelper spHelper, RetrofitService retrofitService) {
        return new DataManagerImpl(spHelper, retrofitService);
    }


    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(AutoValueGson_MyAdapterFactory.create())
                .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    RetrofitService ProvideRetrofitService(Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }
}
