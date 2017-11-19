package com.droid.us.myweatherapp.feature.weather;

import java.lang.ref.WeakReference;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
class WeatherPresenter implements WeatherContractor.Presenter {

    ///////////////////////////////////////////////////////////////////////////
    // Global members
    ///////////////////////////////////////////////////////////////////////////

    private final WeakReference<WeatherContractor.View> mView;

    private final WeatherContractor.Model mModel;

    WeatherPresenter(WeatherContractor.View view) {

        mView = new WeakReference<WeatherContractor.View>(view);

        mModel = new WeatherModel(this);
    }


}
