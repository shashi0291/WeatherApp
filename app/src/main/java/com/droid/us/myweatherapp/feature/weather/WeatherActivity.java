/*
 * File Name : WeatherActivity.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.feature.weather;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.droid.us.myweatherapp.R;
import com.droid.us.myweatherapp.database.model_db.WeatherRealm;
import com.droid.us.myweatherapp.feature.AppBaseActivity;
import com.droid.us.myweatherapp.utility.AppConstants;
import com.droid.us.myweatherapp.utility.LogUtility;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * This is the welcome activity of the project. It allows user to search for the city whose weather
 * is to be looked and it displays the weather details.
 */
public class WeatherActivity extends AppBaseActivity implements WeatherContractor.View,
        PlaceSelectionListener {

    ///////////////////////////////////////////////////////////////////////////
    // Global members
    ///////////////////////////////////////////////////////////////////////////

    private static final String TAG = WeatherActivity.class.getSimpleName();

    // presenter class instance
    private WeatherContractor.Presenter mPresenter;

    // Autocomplete fragment for the search functionality
    PlaceAutocompleteFragment placeAutocompleteFragment;

    // country name
    private String mCountryName;

    // city name
    private String mCityName;

    @BindView(R.id.tv_location)
    TextView tvLocation;

    @BindView(R.id.tv_weather_overview)
    TextView tvWeatherOverview;

    @BindView(R.id.tv_feels_like)
    TextView tvFeelsLike;

    @BindView(R.id.tv_wind_speed)
    TextView tvWindSpeed;

    @BindView(R.id.tv_humidity)
    TextView tvHumidity;

    @BindView(R.id.tv_max_temp)
    TextView tvMaxTemp;

    @BindView(R.id.tv_min_temp)
    TextView tvMinTemp;

    @BindView(R.id.tv_temp)
    TextView tvTemp;

    @BindView(R.id.iv_weather_icon)
    ImageView ivWeatherIcon;

    @BindView(R.id.ll_weather_details)
    LinearLayout llWeatherDetailsAvailable;

    @BindView(R.id.tv_weather_details_not_available)
    TextView tvWeatherDetailsNotAvailable;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    ///////////////////////////////////////////////////////////////////////////
    // Overridden methods
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void onCreateAfterBinding(@Nullable Bundle savedInstanceState) {
        mPresenter = new WeatherPresenter(this);

        initUI();
    }

    /**
     * Control reaches here as soon as user has entered the location whose weather is to be found
     *
     * @param place Google place object
     */
    @Override
    public void onPlaceSelected(Place place) {

        // deduce the country name and place related data using Geocode API
        Geocoder gcd = new Geocoder(this, Locale.getDefault());

        List<Address> address = null;
        try {
            address = gcd.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1);
        } catch (IOException e) {
            LogUtility.e(TAG, "Not able to get the country name" + e.getMessage()); // Country name is not needed
        }

        if (address != null && address.size() > 0) {
            mCountryName = address.get(0).getCountryName();
        }

        // get the city name
        mCityName = (String) place.getName();

        // ask presenter to search for weather
        if (mPresenter != null) {
            mPresenter.fetchCurrentWeatherDetail(place.getLatLng());
        }
    }

    @Override
    public void onError(Status status) {

    }

    @Override
    public String getCountryName() {
        return mCountryName;
    }

    @Override
    public String getCityName() {
        return mCityName;
    }

    /**
     * Populate data on the UI.
     * <p>
     * !!!!! This method could have been written more properly if I had some more time !!!!
     *
     * @param weatherRealm weather object
     */
    @Override
    public void populateDataOnUI(@Nullable WeatherRealm weatherRealm) {
        if (weatherRealm != null) {

            llWeatherDetailsAvailable.setVisibility(View.VISIBLE);
            tvWeatherDetailsNotAvailable.setVisibility(View.GONE);

            if (!TextUtils.isEmpty(weatherRealm.getCityName())) {
                // set the location name
                tvLocation.setText(weatherRealm.getCityName());
            } else {
                tvMinTemp.setText(AppConstants.EMPTY);
            }

            if (!TextUtils.isEmpty(weatherRealm.getWeatherOverview())) {
                //set overview
                tvWeatherOverview.setText(weatherRealm.getWeatherOverview());
            } else {
                tvMinTemp.setText(AppConstants.EMPTY);
            }

            if (!TextUtils.isEmpty(weatherRealm.getWeatherDescription())) {
                tvFeelsLike.setText(weatherRealm.getWeatherDescription());
            } else {
                tvMinTemp.setText(AppConstants.EMPTY);
            }

            if (weatherRealm.getHumidity() != null) {
                String humidity = weatherRealm.getHumidity() + AppConstants.UNIT_HUMIDITY;
                tvHumidity.setText(humidity);
            } else {
                tvMinTemp.setText(AppConstants.EMPTY);
            }

            if (weatherRealm.getTemp() != null) {
                String temp = weatherRealm.getTemp() + AppConstants.DEGREE_CELCIUS;
                tvTemp.setText(temp);
            } else {
                tvMinTemp.setText(AppConstants.EMPTY);
            }

            if (weatherRealm.getTempMax() != null) {
                String maxTemp = weatherRealm.getTempMax() + AppConstants.DEGREE_CELCIUS;
                tvMaxTemp.setText(maxTemp);
            } else {
                tvMinTemp.setText(AppConstants.EMPTY);
            }

            if (weatherRealm.getTempMin() != null) {
                String minTemp = weatherRealm.getTempMin() + AppConstants.DEGREE_CELCIUS;
                tvMinTemp.setText(minTemp);
            } else {
                tvMinTemp.setText(AppConstants.EMPTY);
            }

            if (weatherRealm.getWindSpeed() != null) {
                String windSpeed = weatherRealm.getWindSpeed() + AppConstants.WIND_SPEED;
                tvWindSpeed.setText(windSpeed);
            } else {
                tvWindSpeed.setText(AppConstants.EMPTY);
            }

            // set the icon using Picasso
            if (!TextUtils.isEmpty(weatherRealm.getIcon())) {
                Picasso.with(WeatherActivity.this)
                        .load(getString(R.string.str_open_weather_image_url)
                                + weatherRealm.getIcon() + AppConstants.IMAGE_FORMAT)
                        .into(ivWeatherIcon);
            }
        }
    }

    /**
     * Populate default UI
     */
    @Override
    public void populateDefaultScreenOnUI() {
        llWeatherDetailsAvailable.setVisibility(View.GONE);
        tvWeatherDetailsNotAvailable.setVisibility(View.VISIBLE);
    }

    /**
     * Display progress bar
     */
    @Override
    public void showProgressBar() {
        if (progressBar.getVisibility() != View.VISIBLE) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Dismiss progress bar
     */
    @Override
    public void dismissProgressBar() {
        if (progressBar.getVisibility() != View.GONE) {
            progressBar.setVisibility(View.GONE);
        }
    }


    ///////////////////////////////////////////////////////////////////////////
    // Private methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Initialize the UI components
     */
    private void initUI() {

        placeAutocompleteFragment =
                (PlaceAutocompleteFragment) getFragmentManager()
                        .findFragmentById(R.id.place_autocomplete_fragment);

        placeAutocompleteFragment.setOnPlaceSelectedListener(this);
    }
}
