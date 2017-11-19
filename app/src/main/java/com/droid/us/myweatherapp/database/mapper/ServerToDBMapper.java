/*
 * File Name : ServerToDBMapper.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.database.mapper;

import com.droid.us.myweatherapp.database.model_db.WeatherRealm;
import com.droid.us.myweatherapp.network.server_model.Parent;

/**
 * This class implemets the {@link Mapper} class. Here, the OpenWeatherOrg compatible object is
 * transformed into the {@link WeatherRealm} comatible class so that the DB operations can be
 * performed on that.
 *
 * @author Shashi Pal
 */
public class ServerToDBMapper implements Mapper<Parent, WeatherRealm> {

    @Override
    public WeatherRealm map(Parent parent) {
        WeatherRealm weatherRealm = new WeatherRealm();
        weatherRealm.setCountryName(parent.getCountryName());
        weatherRealm.setCityName(parent.getCityName());
        weatherRealm.setDate(parent.getDt());
        weatherRealm.setHumidity(parent.getMain().getHumidity());
        weatherRealm.setIcon(parent.getWeather().get(0).getIcon());
        weatherRealm.setTemp(parent.getMain().getTemp());
        weatherRealm.setTempMin(parent.getMain().getTempMin());
        weatherRealm.setTempMax(parent.getMain().getTempMax());
        weatherRealm.setWeatherDescription(parent.getWeather().get(0).getDescription());
        weatherRealm.setWeatherOverview(parent.getWeather().get(0).getMain());
        weatherRealm.setWindSpeed(parent.getWind().getSpeed());
        return weatherRealm;
    }
}
