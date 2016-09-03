package com.androidbootstrap.inject.module;

import android.content.Context;

import com.androidbootstrap.data.base.MyAdapterFactory;
import com.androidbootstrap.util.SpHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

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
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(MyAdapterFactory.create())
                .create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        return null;
    }
}
