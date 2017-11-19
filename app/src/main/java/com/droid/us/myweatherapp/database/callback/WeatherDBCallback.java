package com.droid.us.myweatherapp.database.callback;

import io.reactivex.annotations.NonNull;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
public interface WeatherDBCallback {
    void onSuccess();

    void onError(@NonNull Throwable throwable);
}
