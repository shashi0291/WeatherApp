package com.droid.us.myweatherapp.network.callback;

import com.droid.us.myweatherapp.network.server_model.Parent;

import io.reactivex.annotations.NonNull;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
public interface WeatherNWCallback {
    void onSuccess(Parent weather);

    void onError(@NonNull Throwable throwable);
}
