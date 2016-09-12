package com.androidbootstrap.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/9
 * @since 1.0
 */
public class NetUtil {
public static Context context;
    public static void init(Context context){
        NetUtil.context=context;
    }
    /**
     * 判断网络连接是否打开,包括移动数据连接
     *
     * @return 是否联网
     */
    public static boolean isNetworkEnable() {
        boolean netstate = false;
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {

            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {

                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {

                        netstate = true;
                        break;
                    }
                }
            }
        }
        return netstate;
    }


    /**
     * 检测网络是否连接上
     *
     * @return 是否能上网
     */
    public static boolean isNetworkConnected() {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    /**
     * GPS是否打开
     *
     * @return Gps是否可用
     */
    public static boolean isGpsEnabled() {
        LocationManager lm = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 检测当前打开的网络类型是否WIFI
     *
     * @return 是否是Wifi上网
     */
    public static boolean isWifiConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI;
    }

    /**
     * 检测当前打开的网络类型是否3G
     *
     * @return 是否是3G上网
     */
    public static boolean is3GConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * 检测当前开打的网络类型是否4G
     *
     * @return 是否是4G上网
     */
    public static boolean is4GConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.isConnectedOrConnecting()) {
            if (activeNetInfo.getType() == TelephonyManager.NETWORK_TYPE_LTE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 只是判断WIFI
     *
     * @return 是否打开Wifi
     */
    public static boolean isWiFiEnable() {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        return wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING;
    }

}
