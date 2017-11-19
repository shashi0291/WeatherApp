/*
 * File Name : Clouds.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.network.server_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Bean class - Represents cloud data
 *
 * @author Shashi Pal
 */
public class Clouds {
    @SerializedName("all")
    @Expose
    private Integer all;

    @SuppressWarnings("unused") // might be used later
    public Integer getAll() {
        return all;
    }

    @SuppressWarnings("unused") // might be used later
    public void setAll(Integer all) {
        this.all = all;
    }
}
