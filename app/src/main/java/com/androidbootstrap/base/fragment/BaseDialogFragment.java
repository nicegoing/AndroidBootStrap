package com.androidbootstrap.base.fragment;

import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * 对DialogFragment的封装，处理重复调用DialogFragment报错的Bug
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/6
 * @since 1.0
 */
public class BaseDialogFragment extends DialogFragment {


    @Override
    public final void show(FragmentManager manager, String tag) {
        showAllowingStateLoss(manager, tag);
    }

    /**
     * 解决重复调用show报错的问题
     *
     * @param manager fragment manager
     * @param tag     fragment tag
     */
    private void showAllowingStateLoss(@NonNull FragmentManager manager, String tag) {
        final FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(this);
        transaction.add(this, tag);
        transaction.commitAllowingStateLoss();
    }
}
