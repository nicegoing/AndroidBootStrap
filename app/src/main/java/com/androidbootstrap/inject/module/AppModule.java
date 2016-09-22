//package com.androidbootstrap.inject.module;
//
//import android.content.Context;
//
//import com.androidbootstrap.constant.Constants;
//import com.androidbootstrap.data.DataManagerImpl;
//import com.androidbootstrap.data.base.AutoValueGson_MyAdapterFactory;
//import com.androidbootstrap.data.base.DataManager;
//import com.androidbootstrap.data.db.DBOpenHelper;
//import com.androidbootstrap.data.retrofit.RetrofitService;
//import com.androidbootstrap.util.SpHelper;
//import com.github.simonpercic.oklog3.OkLogInterceptor;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.squareup.sqlbrite.BriteDatabase;
//import com.squareup.sqlbrite.SqlBrite;
//
//import java.util.concurrent.TimeUnit;
//
//import javax.inject.Named;
//import javax.inject.Singleton;
//
//import dagger.Module;
//import dagger.Provides;
//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
//import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;
//import rx.schedulers.Schedulers;
//
///**
// * @author puhanhui
// * @version 1.0
// * @date 2016/8/12
// * @since 1.0
// */
//@Module
//public class AppModule {
//    protected final Context context;
//
//    public AppModule(Context context) {
//        this.context = context;
//    }
//
//    @Provides
//    Context provideContext() {
//        return context;
//    }
//
//
//    @Provides
//    @Singleton
//    SpHelper provideSpHelper(Context context) {
//        return new SpHelper(context);
//    }
//
//    @Provides
//    @Singleton
//    DBOpenHelper ProvideDbOpenHelper(Context context) {
//        return new DBOpenHelper(context);
//    }
//
//    @Provides
//    @Singleton
//    BriteDatabase ProvideBriteDb(DBOpenHelper dbOpenHelper) {
//        BriteDatabase briteDatabase = SqlBrite.create().wrapDatabaseHelper(dbOpenHelper, Schedulers.io());
//        briteDatabase.setLoggingEnabled(true);
//        return briteDatabase;
//    }
//
//
//    @Provides
//    @Singleton
//    DataManager provideDataManager(SpHelper spHelper, RetrofitService retrofitService, BriteDatabase briteDatabase) {
//        return new DataManagerImpl(spHelper, retrofitService, briteDatabase);
//    }
//
//
//    @Provides
//    @Singleton
//    Gson provideGson() {
//        return new GsonBuilder()
//                .registerTypeAdapterFactory(AutoValueGson_MyAdapterFactory.create())
//                .create();
//    }
//
//    @Provides
//    @Singleton
//    OkHttpClient provideOkHttpClient() {
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkLogInterceptor okLogInterceptor = OkLogInterceptor.builder().build();
//
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
//                .connectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
//                .addInterceptor(okLogInterceptor)
//                .addInterceptor(loggingInterceptor)
//                //                .addNetworkInterceptor(new StethoInterceptor())
//                .build();
//
//        return okHttpClient;
//    }
//
//
//    @Provides
//    @Singleton
//    @Named("noRxJava")
//    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
//        return new Retrofit.Builder().baseUrl(Constants.BASE_URL)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//    }
//
//    @Provides
//    @Singleton
//    @Named("rxJava")
//    Retrofit ProvideRetrofitWithRxJava(Gson gson, OkHttpClient okHttpClient) {
//        return new Retrofit.Builder().baseUrl(Constants.BASE_URL)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .build();
//    }
//
//    @Provides
//    @Singleton
//    RetrofitService ProvideRetrofitService(@Named("rxJava") Retrofit retrofit) {
//        return retrofit.create(RetrofitService.class);
//    }
//
//}
