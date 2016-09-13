package com.androidbootstrap.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.androidbootstrap.constant.Constants;
import com.androidbootstrap.data.base.DataManager;
import com.androidbootstrap.bean.Person;
import com.androidbootstrap.data.retrofit.RetrofitService;
import com.androidbootstrap.rx.ApiResponse;
import com.androidbootstrap.rx.RxResultHelper;
import com.androidbootstrap.rx.RxSchedulersHelper;
import com.androidbootstrap.util.LogUtil;
import com.androidbootstrap.util.SpHelper;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/6
 * @since 1.0
 */
public class DataManagerImpl implements DataManager {
    SpHelper        spHelper;
    RetrofitService retrofitService;
    BriteDatabase   db;

    public DataManagerImpl(SpHelper spHelper, RetrofitService retrofitService, BriteDatabase briteDatabase) {
        this.spHelper = spHelper;
        this.retrofitService = retrofitService;
        this.db = briteDatabase;
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

    /**
     * step1 从网络获取数据转换为Person类
     * step2 获取数据后同时新建个子线程保存数据到数据库
     *
     * @return 发送获取的数据的发布者
     */
    @Override
    public Observable<Person> getProfile() {
        return retrofitService.getProfile()
                .doOnNext(new Action1<ApiResponse<Person>>() {
                    @Override
                    public void call(final ApiResponse<Person> apiResponse) {
                        final Scheduler.Worker worker = Schedulers.io().createWorker();
                        worker.schedule(new Action0() {
                            @Override
                            public void call() {
                                if (apiResponse.result() != null) {
                                    writeProfile(apiResponse.result());
                                    LogUtil.i("插入数据库");
                                    readProfile();
                                }
                                worker.unsubscribe();
                            }
                        });
                    }
                })
                .compose(RxSchedulersHelper.<ApiResponse<Person>>applyIoSchedulers())
                .compose(RxResultHelper.<Person>handleResult());
    }

    /**
     * 写入到数据库
     *
     * @param person
     */
    public void writeProfile(Person person) {
        ContentValues contentValues = Person.FACTORY.marshal(person).asContentValues();
        contentValues.remove("_id");
        db.insert(Person.TABLE_NAME, contentValues);
    }

    /**
     * 打印数据库Person数据
     */
    void readProfile() {
        List<Person> persons = new ArrayList<>();
        Cursor cursor = db.query(Person.SELECT_ALL, new String[]{});
        cursor.moveToPrevious();
        while (cursor.moveToNext()) {
            Person person = Person.MAPPER.map(cursor);
            persons.add(person);
            Log.i("person:", person.age() + "--" + person.name() + "--" + person.gender());
        }
        cursor.close();
    }
}
