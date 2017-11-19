package com.droid.us.myweatherapp.utility;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.droid.us.myweatherapp.BuildConfig;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
public class LogUtility {

    public static final String TAG = LogUtility.class.getSimpleName();

    /**
     * Display a verbose log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     */
    public static void v(@NonNull String tag, @NonNull String msg) {

        if (BuildConfig.DEBUG) {
            Log.v(tag, msg);
        }
    }

    /**
     * Display a verbose log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     * @param tr An exception to be attached with log
     */
    public static void v(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {

        if (BuildConfig.DEBUG) {
            Log.v(tag, msg, tr);
        }
    }

    /**
     * Display a info log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     */
    public static void i(@NonNull String tag, @NonNull String msg) {

        if (BuildConfig.DEBUG) {
            Log.i(tag, msg);
        }
    }

    /**
     * Display a info log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     * @param tr An exception to be attached with log
     */
    public static void i(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {

        if (BuildConfig.DEBUG) {
            Log.i(tag, msg, tr);
        }
    }

    /**
     * Display a debug log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     */
    public static void d(@NonNull String tag, @NonNull String msg) {

        if (BuildConfig.DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * Display a debug log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     * @param tr An exception to be attached with log
     */
    public static void d(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {

        if (BuildConfig.DEBUG) {
            Log.d(tag, msg, tr);
        }
    }

    /**
     * Display a warn log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     */
    public static void w(@NonNull String tag, @NonNull String msg) {

        if (BuildConfig.DEBUG) {
            Log.w(tag, msg);
        }
    }

    /**
     * Display a warn log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     * @param tr An exception to be attached with log
     */
    public static void w(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {

        if (BuildConfig.DEBUG) {
            Log.w(tag, msg, tr);
        }
    }

    /**
     * Display a error log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     */
    public static void e(@NonNull String tag, @NonNull String msg) {

        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }

    /**
     * Display a error log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     * @param tr An exception to be attached with log
     */
    public static void e(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {

        if (BuildConfig.DEBUG) {
            Log.e(tag, msg, tr);
        }
    }

    /**
     * Display a terrible failure log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     */
    public static void wtf(@NonNull String tag, @NonNull String msg) {

        if (BuildConfig.DEBUG) {
            Log.wtf(tag, msg);
        }
    }

    /**
     * Display a terrible failure log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     * @param tr An exception to be attached with log
     */
    public static void wtf(@NonNull String tag, @NonNull String msg, @Nullable Throwable tr) {

        if (BuildConfig.DEBUG) {
            Log.wtf(tag, msg, tr);
        }
    }
}
