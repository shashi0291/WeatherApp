/*
 * File Name : WeatherContractor.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.feature.weather;

import com.droid.us.myweatherapp.database.model_db.WeatherRealm;
import com.droid.us.myweatherapp.network.callback.WeatherNWCallback;
import com.droid.us.myweatherapp.network.server_model.Parent;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * This class provides the basic schema for the MVP design pattern.
 * Also, helps to fecilitate the separation of concerns as much as possible.
 *
 * @author Shashi Pal
 */
class WeatherContractor {

    /**
     * The weather Presenter interface - The manager :)
     */
    interface Presenter {

        void fetchCurrentWeatherDetail(LatLng latLng);

        void fetchLastSearchedCity();

        void saveDataInDB(WeatherRealm weatherRealm);
    }

    /**
     * The weather model - the brain :D
     */
    interface Model {

        void getWeatherDetail(LatLng latLng, WeatherNWCallback callback);

        Single<Parent> fetchWeatherForCurrentDat(LatLng latLng);

        Flowable<WeatherRealm> fetchLastSearchedCityData();

        Flowable<WeatherRealm> saveDataInDB(WeatherRealm weatherRealm);
    }

    /**
     * The view - really dumb :p
     */
    interface View {

        String getCountryName();

        String getCityName();

        void populateDataOnUI(WeatherRealm weatherRealm);

        void populateDefaultScreenOnUI();

        void showProgressBar();

        void dismissProgressBar();
    }
}
