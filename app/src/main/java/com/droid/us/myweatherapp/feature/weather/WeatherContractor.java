package com.droid.us.myweatherapp.feature.weather;

import android.location.Location;

import com.droid.us.myweatherapp.database.callback.WeatherDBCallback;
import com.droid.us.myweatherapp.database.model_db.WeatherRealm;
import com.droid.us.myweatherapp.network.callback.WeatherNWCallback;
import com.droid.us.myweatherapp.network.server_model.Parent;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Single;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
class WeatherContractor {

    interface Presenter {

        void fetchCurrentWeatherDetail(LatLng latLng);
    }

    interface Model {

        void getWeatherDetail(LatLng latLng, WeatherNWCallback callback);

        Single<Parent> fetchWeatherForCurrentDat(LatLng latLng);

        void updateDatabase(WeatherRealm weatherRealm, WeatherDBCallback weatherDBCallback);

    }

    interface View {

        String getCountryName();

        String getCityName();
    }
}
