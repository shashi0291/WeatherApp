package com.droid.us.myweatherapp.feature.weather;

import com.droid.us.myweatherapp.database.callback.WeatherDBCallback;
import com.droid.us.myweatherapp.database.model_db.WeatherRealm;
import com.droid.us.myweatherapp.network.callback.WeatherNWCallback;
import com.droid.us.myweatherapp.network.server_model.Parent;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
class WeatherContractor {

    interface Presenter {

        void fetchCurrentWeatherDetail(LatLng latLng);

        void fetchLastSearchedCity();

        void saveDataInDB(WeatherRealm weatherRealm);
    }

    interface Model {

        void getWeatherDetail(LatLng latLng, WeatherNWCallback callback);

        Single<Parent> fetchWeatherForCurrentDat(LatLng latLng);

        void updateDatabase(WeatherRealm weatherRealm, WeatherDBCallback weatherDBCallback);

        Flowable<WeatherRealm> fetchLastSearchedCityData();

        Flowable<WeatherRealm> saveDataInDB(WeatherRealm  weatherRealm);

    }

    interface View {

        String getCountryName();

        String getCityName();

        void populateDataOnUI(WeatherRealm weatherRealm);

        void populateDefaultScreenOnUI();

        void showProgressBar();

        void dismissProgressBar();
    }
}
