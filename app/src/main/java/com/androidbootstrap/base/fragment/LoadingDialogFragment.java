package com.androidbootstrap.base.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.library.R;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/14
 * @since 1.0
 */
public class LoadingDialogFragment extends BaseDialogFragment {
    private String message;
    private boolean mCancelable;
    private boolean mOnBackCancelable = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        mCancelable = cancel;
    }

    public void setCanceledOnBackPressed(boolean onBackCancelable) {
        this.mOnBackCancelable = onBackCancelable;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_loading, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView messageTv = (TextView) view.findViewById(R.id.dialog_message);
        if (message != null) {
            messageTv.setText(message);
            messageTv.setVisibility(View.VISIBLE);
        } else {
            messageTv.setVisibility(View.GONE);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCancelable(isCancelable());
        dialog.setCanceledOnTouchOutside(mCancelable);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    return !mOnBackCancelable;
                }
                return false;
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        return dialog;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
