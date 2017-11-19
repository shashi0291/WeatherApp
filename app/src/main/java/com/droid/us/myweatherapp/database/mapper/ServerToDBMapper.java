package com.droid.us.myweatherapp.database.mapper;

import com.droid.us.myweatherapp.database.model_db.WeatherRealm;
import com.droid.us.myweatherapp.network.server_model.Parent;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
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
        return weatherRealm;
    }
}
