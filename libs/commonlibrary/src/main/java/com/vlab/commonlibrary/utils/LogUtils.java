package com.vlab.commonlibrary.utils;

import android.util.Log;


/**
 * Created by vlab.smartapps@gmail.com on 5/5/16.
 */
public class LogUtils {

    private static boolean isDebuggable = true;

    private LogUtils() {

    }

    /**
     * Default is true for showing log, should set false for disabling log in release mode
     * @param isShow boolean
     */
    public void setShowLog(boolean isShow){
        isDebuggable = isShow;
    }

    // log verbal
    public static void v(String tag, String msg) {
        if (msg != null && isDebuggable) {
            Log.v(tag, msg);
        }
    }

    // log debug
    public static void d(String tag, String msg) {
        if (msg != null && isDebuggable) {
            Log.d(tag, msg);
        }
    }

    // log info
    public static void i(String tag, String msg) {
        if (msg != null && isDebuggable) {
            Log.i(tag, msg);
        }
    }

    // log error
    public static void e(String tag, String msg) {
        if (msg != null && isDebuggable) {
            Log.e(tag, msg);
        }
    }

    public static void printStackTrace(Exception e) {
        if (e != null && isDebuggable) {
            e.printStackTrace();
        }
    }

}
