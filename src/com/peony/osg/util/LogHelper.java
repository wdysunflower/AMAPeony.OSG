package com.peony.osg.util;

import android.util.Log;

import java.io.UnsupportedEncodingException;

/**
 * Created by wdynetposa on 2014/12/1.
 */
public class LogHelper {
    private static boolean DEBUG = false;

    static String className;
    static String methodName;
    static int lineNumber;

    public static void e(String message) {
        if (!DEBUG) return;
        Log.e(className, createLog(message));
    }

    public static void i(String message) {
        if (!DEBUG) return;
        Log.i(className, createLog(message));
    }

    public static void d(String message) {
        if (!DEBUG) return;
        Log.d(className, createLog(message));
    }

    public static void v(String message) {
        if (!DEBUG) return;
        Log.v(className, createLog(message));
    }

    public static void w(String message) {
        if (!DEBUG) return;
        Log.w(className, createLog(message));
    }

    public static void wtf(String message) {
        if (!DEBUG) return;
        Log.wtf(className, createLog(message));
    }

    private static String createLog(String log) {
        getMethodNames(new Throwable().getStackTrace());

        StringBuffer buffer = new StringBuffer();

        buffer.append("[");
        buffer.append(methodName);
        buffer.append(":");
        buffer.append(lineNumber);
        buffer.append("]");
        buffer.append(log);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }
}
