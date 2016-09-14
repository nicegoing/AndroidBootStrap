package com.androidbootstrap.constant;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/9
 * @since 1.0
 */
public class Constants {
    public static final String SP_NAME              = "SP_NAME";
    public static final String BASE_URL             = "http://10.37.5.177:8080/";
    public static final String PERSON_URL           = "person.json";
    public static final int    HTTP_CONNECT_TIMEOUT = 10000;

    public static final class SP_KEY {
        public static final String EMAIL = "email";
    }

    public static final class LOAD_TYPE {
        public static final int FIRST_IN  = 1;
        public static final int REFRESH   = 2;
        public static final int LOAD_MORE = 3;
    }
}
