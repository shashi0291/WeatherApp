/*
 * File Name : Main.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.network.server_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Bean class - represents weather related details
 *
 * @author Shashi pal
 */
//// TODO: 11/19/17 to change the class name later
public class Main {
    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;
    @SerializedName("sea_level")
    @Expose
    private Double seaLevel;
    @SerializedName("grnd_level")
    @Expose
    private Double grndLevel;

    public Double getTemp() {
        return temp;
    }

    @SuppressWarnings("unused")
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    @SuppressWarnings("unused")
    public Double getPressure() {
        return pressure;
    }

    @SuppressWarnings("unused")
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    @SuppressWarnings("unused")
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getTempMin() {
        return tempMin;
    }

    @SuppressWarnings("unused")
    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    @SuppressWarnings("unused")
    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    @SuppressWarnings("unused")
    public Double getSeaLevel() {
        return seaLevel;
    }

    @SuppressWarnings("unused")
    public void setSeaLevel(Double seaLevel) {
        this.seaLevel = seaLevel;
    }

    @SuppressWarnings("unused")
    public Double getGrndLevel() {
        return grndLevel;
    }

    @SuppressWarnings("unused")
    public void setGrndLevel(Double grndLevel) {
        this.grndLevel = grndLevel;
    }
}
