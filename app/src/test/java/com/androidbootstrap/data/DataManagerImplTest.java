package com.androidbootstrap.data;

import android.support.annotation.Nullable;

import com.androidbootstrap.bean.Address;
import com.androidbootstrap.bean.Person;
import com.androidbootstrap.data.retrofit.RetrofitService;
import com.androidbootstrap.rx.ApiResponse;
import com.androidbootstrap.util.SpHelper;
import com.squareup.sqlbrite.BriteDatabase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/21
 * @since 1.0
 */
@PrepareForTest(BriteDatabase.class)
@RunWith(PowerMockRunner.class)
public class DataManagerImplTest {
    @Mock
    RetrofitService retrofitService;
    @Mock
    SpHelper        spHelper;
    BriteDatabase briteDatabase;
    private DataManagerImpl dataManager;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        briteDatabase = PowerMockito.mock(BriteDatabase.class);
        dataManager = Mockito.spy(new DataManagerImpl(spHelper, retrofitService, briteDatabase));
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });

    }

    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }


    @Test
    public void loadProfile_200OkResponse_invokesCorrectApiCalls() {
        //Given
        Mockito.doNothing().when(dataManager).readProfile();
        Mockito.doNothing().when(dataManager).writeProfile(Mockito.mock(Person.class));
        when(retrofitService.loadProfile()).thenReturn(rx.Observable.just(getApiResponsePerson()));
        //When
        TestSubscriber<Person> subscriber = new TestSubscriber<>();
        dataManager.loadProfile().subscribe(subscriber);

        //Then
        subscriber.awaitTerminalEvent();
        subscriber.assertNoErrors();
        List<Person> onNextEvents = subscriber.getOnNextEvents();
        Assert.assertEquals("ddddd", onNextEvents.get(0).name());
        verify(retrofitService).loadProfile();

    }

    private ApiResponse<Person> getApiResponsePerson() {
        return new ApiResponse<Person>() {
            @Override
            public int code() {
                return 200;
            }

            @Override
            public String msg() {
                return "success";
            }

            @Override
            public Person result() {
                return new Person() {
                    @Nullable
                    @Override
                    public String name() {
                        return "ddddd";
                    }

                    @Nullable
                    @Override
                    public String gender() {
                        return "1";
                    }

                    @Nullable
                    @Override
                    public String age() {
                        return "12";
                    }

                    @Nullable
                    @Override
                    public Address address() {
                        return null;
                    }

                    @Override
                    public long _id() {
                        return 0;
                    }
                };
            }
        };
    }


}