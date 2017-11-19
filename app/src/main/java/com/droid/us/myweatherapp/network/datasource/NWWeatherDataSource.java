package com.droid.us.myweatherapp.network.datasource;

import com.droid.us.myweatherapp.MyWeatherApplication;
import com.droid.us.myweatherapp.R;
import com.droid.us.myweatherapp.network.BaseServer;
import com.droid.us.myweatherapp.network.server_model.Parent;
import com.droid.us.myweatherapp.network.service.WeatherService;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Single;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
public class NWWeatherDataSource {

    public Single<Parent> getCurrentWeatherDetail(LatLng latLng, String appId) {
        return BaseServer
                .getRetrofit(MyWeatherApplication.getInstance()
                        .getString(R.string.str_open_weather_base_url))
                .create(WeatherService.class)
                .getCurrentWeatherDetails(String.valueOf(latLng.latitude),
                        String.valueOf(latLng.longitude),
                        appId);
    }
}
