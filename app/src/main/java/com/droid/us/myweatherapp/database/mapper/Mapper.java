/*
 * File Name : WeatherModel.java
 * Project : Mapper.java
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.database.mapper;

/**
 * Mapper class responsible to provide an interface to the implementing class, so that the InParam
 * object can be made compatible with the actual function.
 *
 * @author Shashi Pal
 */
interface Mapper<InParam, OutParam> {

    OutParam map(InParam param);

}
