package com.androidbootstrap.ui.main;

import com.androidbootstrap.bean.Person;
import com.androidbootstrap.ui.base.IStateView;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/3
 * @since 1.0
 */
public interface IMainView extends IStateView {
    void writeEmail(String email);

    String readEmail();

    void setProfile(Person Person);


}
