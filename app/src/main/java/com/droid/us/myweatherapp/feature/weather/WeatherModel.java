package com.droid.us.myweatherapp.feature.weather;

import io.reactivex.disposables.CompositeDisposable;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
class WeatherModel implements WeatherContractor.Model {

    private final WeatherContractor.Presenter mWeatherPresenter;

    private final CompositeDisposable mDisposable;

    /**
     * Initialize the Model using the presenter, so that it can facilitate the data transfer
     * between them.
     *
     * @param weatherPresenter Presenter that contains all the business logic.
     */
    WeatherModel (WeatherContractor.Presenter weatherPresenter) {

        mWeatherPresenter = weatherPresenter;

        mDisposable = new CompositeDisposable();
    }
}
