package com.androidbootstrap.ui.base;

import com.library.ui.base.IView;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/13
 * @since 1.0
 */
public interface IStateView extends IView {
    /**
     * 设置view显示的状态
     *
     * @param state 状态为empty，loading,content,error等
     */
    void setViewState(int state);

    /**
     * show dialog
     *
     * @param dialogType 类型定义在{@link com.library.constant.Constant.DIALOG_TYPE DIALOG_TYPE}
     */
    void displayDialog(int dialogType);

    /**
     * close dialog
     *
     * @param dialogType 类型定义在{@link com.library.constant.Constant.DIALOG_TYPE DIALOG_TYPE}
     */
    void hideDialog(int... dialogType);
}
