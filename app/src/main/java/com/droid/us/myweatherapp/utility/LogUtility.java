/*
 * File Name : LogUtility.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.utility;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.droid.us.myweatherapp.BuildConfig;

/**
 * Class responsible to customize the Android logging activity. Logs will only be printed when the
 * app is running in debug mode.
 *
 * @author Shashi Pal
 */
public class LogUtility {

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
     * Display a error log message.
     * @param tag class where the log call occurs.
     * @param msg message to be displayed in log.
     */
    public static void e(@NonNull String tag, @NonNull String msg) {

        if (BuildConfig.DEBUG) {
            Log.e(tag, msg);
        }
    }
}
