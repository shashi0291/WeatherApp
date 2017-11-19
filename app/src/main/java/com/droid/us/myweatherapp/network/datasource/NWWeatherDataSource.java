/*
 * File Name : NWWeatherDataSource.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.network.datasource;

import com.droid.us.myweatherapp.MyWeatherApplication;
import com.droid.us.myweatherapp.R;
import com.droid.us.myweatherapp.network.BaseServer;
import com.droid.us.myweatherapp.network.server_model.Parent;
import com.droid.us.myweatherapp.network.service.WeatherService;
import com.droid.us.myweatherapp.utility.NWConstantUtility;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Single;

/**
 * Gets teh Retrofit instance, interacts with {@link WeatherService} to get the actual URL.
 * And then initiates the web call request.
 *
 * @author Shashi Pal
 */
public class NWWeatherDataSource {

    /**
     * Initiates the web service call by getting the instance of retrofit
     * @param latLng lat long
     * @param appId app id (saved locally)
     * @return Single<ServerWeatherObject>
     */
    public Single<Parent> getCurrentWeatherDetail(LatLng latLng, String appId) {

        return BaseServer
                .getRetrofit(MyWeatherApplication.getInstance()
                        .getString(R.string.str_open_weather_base_url))
                .create(WeatherService.class)
                .getCurrentWeatherDetails(String.valueOf(latLng.latitude),
                        String.valueOf(latLng.longitude),
                        appId,
                        NWConstantUtility.UNIT_METRIC);
    }
}
