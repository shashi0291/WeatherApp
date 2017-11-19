/*
 * File Name : Coord.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.network.server_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Bean class - represents the coordinate data
 *
 * @author Shashi Pal
 */
public class Coord {

    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("lat")
    @Expose
    private Double lat;

    @SuppressWarnings("unused")
    public Double getLon() {
        return lon;
    }

    @SuppressWarnings("unused")
    public void setLon(Double lon) {
        this.lon = lon;
    }

    @SuppressWarnings("unused")
    public Double getLat() {
        return lat;
    }

    @SuppressWarnings("unused")
    public void setLat(Double lat) {
        this.lat = lat;
    }
}
