package com.library.ui;

/**
 * View接口的基类
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/6
 * @since 1.0
 */
public interface IBaseView {
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
