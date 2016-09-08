package com.androidbootstrap.ui.main;

import com.library.ui.IBaseView;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/3
 * @since 1.0
 */
public interface IMainView extends IBaseView {
    void writeEmail(String email);

    String readEmail();
}
