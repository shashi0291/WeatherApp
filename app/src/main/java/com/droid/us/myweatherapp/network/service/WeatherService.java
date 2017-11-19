package com.droid.us.myweatherapp.network.service;

import com.droid.us.myweatherapp.network.server_model.Parent;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.droid.us.myweatherapp.utility.NWConstantUtility.APP_ID;
import static com.droid.us.myweatherapp.utility.NWConstantUtility.LATITUDE;
import static com.droid.us.myweatherapp.utility.NWConstantUtility.LONGITUDE;
import static com.droid.us.myweatherapp.utility.NWConstantUtility.WEATHER;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
public interface WeatherService {

    //http://api.openweathermap.org/data/2.5/weather?lat=40.7128&lon=74.0060&appid=e37dececec7a87674cc2018b3ecadd2e

    @GET(WEATHER)
    Single<Parent> getCurrentWeatherDetails(
      @Query(LATITUDE) String latitude,
      @Query(LONGITUDE) String longitude,
      @Query(APP_ID) String appId
    );
}
