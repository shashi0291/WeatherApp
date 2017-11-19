package com.droid.us.myweatherapp.database.model_db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
public class WeatherRealm extends RealmObject {

    @PrimaryKey
    private String cityName;

    private String countryName;

    private String weatherOverview;

    private String weatherDescription;

    private String icon;

    private Double temp;

    private Double humidity;

    private Double temp_min;

    private Double temp_max;

    private Double wind_speed;

    private long date;

    public WeatherRealm() {}

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherOverview() {
        return weatherOverview;
    }

    public void setWeatherOverview(String overview) {
        this.weatherOverview = overview;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String description) {
        this.weatherDescription = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getTempMin() {
        return temp_min;
    }

    public void setTempMin(Double tempMin) {
        this.temp_min = tempMin;
    }

    public Double getTempMax() {
        return temp_max;
    }

    public void setTempMax(Double temp_max) {
        this.temp_max = temp_max;
    }

    public Double getWindSpeed() {
        return wind_speed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.wind_speed = windSpeed;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
