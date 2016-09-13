package com.androidbootstrap.rx.error;

import com.androidbootstrap.util.NetUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class ErrorHanding {

    public ErrorHanding() {
    }

    /**
     * 对错误数据进行处理，返回对应字符串提示信息
     *
     * @param e 错误数据接口
     * @return 返回对应错误提示信息
     */
    public static String handleError(ErrorBundle e) {
        e.getException().printStackTrace();
        String message;
        if (!NetUtil.isNetworkConnected()) {
            message = "网络中断，请检查您的网络状态";
        } else if (e.getException() instanceof SocketTimeoutException) {
            message = "网络连接超时，请检查您的网络状态";
        } else if (e.getException() instanceof ConnectException) {
            message = "连接异常，请检查您的网络状态ConnectException";
        } else if (e.getException() instanceof NetworkConnectionException) {
            message = "网络中断，请检查您的网络状态";
        } else if (e.getException() instanceof ServerException) {
            int code = ((ServerException) e.getException()).getCode();
            //在这里你可以获取code来判断是什么类型，进行相应处理，比如token失效了可以实现跳转到登录页面
            message = e.getErrorMessage();
        } else {
            message = "连接服务器失败,请稍后再试";
        }
        return message;
    }
}
