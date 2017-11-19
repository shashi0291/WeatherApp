/*
 * File Name : WeatehrNWCallback.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.network.callback;

import com.droid.us.myweatherapp.network.server_model.Parent;

import io.reactivex.annotations.NonNull;

/**
 * Provides status callbacks for the network operation
 *
 * @author Shashi Pal
 */
public interface WeatherNWCallback {

    void onSuccess(Parent weather);

    void onError(@NonNull Throwable throwable);
}
