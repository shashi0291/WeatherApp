/*
 * File Name : WeatherDBCallback.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.database.callback;

import io.reactivex.annotations.NonNull;

/**
 * DB call backs
 *
 * @author Shashi Pal
 */
public interface WeatherDBCallback {

    @SuppressWarnings("unused") // to be used later
    void onSuccess();

    @SuppressWarnings("unused") // to be used later
    void onError(@NonNull Throwable throwable);
}
