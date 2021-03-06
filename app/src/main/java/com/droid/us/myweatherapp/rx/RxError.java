/*
 * File Name : RxError.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.rx;

import android.text.TextUtils;

import com.droid.us.myweatherapp.utility.LogUtility;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Basic schema to handle the RxError scenarios (implementing classes can override the implementation)
 *
 * @author Shashi Pal
 */
public class RxError implements Consumer<Throwable> {
    private final String tag;

    public RxError() {
        this("RxError :: ");
    }

    public RxError(@android.support.annotation.NonNull String tag) {
        this.tag = tag;
    }

    @Override
    public void accept(@NonNull Throwable throwable) throws Exception {
        String message = throwable.getLocalizedMessage();
        if (!TextUtils.isEmpty(message)) {
            LogUtility.e(tag, message);
        } else {
            acceptNow(throwable);
        }
    }

    public void acceptNow(@NonNull Throwable throwable) throws Exception {
        //Override this method to get the actual error
    }
}
