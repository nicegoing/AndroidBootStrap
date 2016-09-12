package com.androidbootstrap.rx.error;

/**
 * 接收服务器返回的错误码和信息，对异常进行处理
 */
public class ServerException extends Exception {


    private int code;

    public ServerException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
