package com.androidbootstrap.ui.main;

import com.androidbootstrap.bean.Person;
import com.androidbootstrap.data.base.DataManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/21
 * @since 1.0
 */
public class MainPresenterTest {
    @Mock
    DataManager dataManager;
    @Mock
    IMainView   iMainView;
    private MainPresenter mainPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainPresenter(dataManager);
        mainPresenter.attachView(iMainView);
    }

    @Test
    public void loadProfile_getProfile() {
        Person person=Mockito.mock(Person.class);
        Mockito.when(dataManager.loadProfile()).thenReturn(Observable.just(person));
        mainPresenter.loadProfile();
        Mockito.verify(iMainView).setViewState(3);
        Mockito.verify(iMainView).setProfile(person);
    }

    @After
    public void tearDown() throws Exception {

    }

}