package com.droid.us.myweatherapp.feature.weather;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class WeatherActivity extends AppBaseActivity implements WeatherContractor.View,
        PlaceSelectionListener {

    ///////////////////////////////////////////////////////////////////////////
    // Global members
    ///////////////////////////////////////////////////////////////////////////

    private static final String TAG = WeatherActivity.class.getSimpleName();

    private WeatherContractor.Presenter mPresenter;

    PlaceAutocompleteFragment placeAutocompleteFragment;

    private String mCountryName;

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

    @Override
    public void onPlaceSelected(Place place) {
        LogUtility.d(TAG, "Place.getLatlong = " + place.getLatLng());
        LogUtility.d(TAG, "place.getAddress = " + place.getAddress().toString());

        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> address = null;
        try {
            address = gcd.getFromLocation(place.getLatLng().latitude, place.getLatLng().longitude, 1);
        } catch (IOException e) {
            // todo not able to find the address
            e.printStackTrace();
        }

        if (address != null && address.size() > 0) {
            mCountryName = address.get(0).getCountryName();
            mCityName = (String) place.getName();
        }

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

    @Override
    public void populateDataOnUI(@Nullable WeatherRealm weatherRealm) {
        if (weatherRealm != null) {

            llWeatherDetailsAvailable.setVisibility(View.VISIBLE);
            tvWeatherDetailsNotAvailable.setVisibility(View.GONE);

            if (!TextUtils.isEmpty(weatherRealm.getCityName())) {
                // set the location name
                tvLocation.setText(weatherRealm.getCityName());
            }

            if (!TextUtils.isEmpty(weatherRealm.getWeatherOverview())) {
                //set overview
                tvWeatherOverview.setText(weatherRealm.getWeatherOverview());
            }

            if (!TextUtils.isEmpty(weatherRealm.getWeatherDescription())) {
                tvFeelsLike.setText(weatherRealm.getWeatherDescription());
            }

            if (weatherRealm.getHumidity() != null) {
                String humidity = weatherRealm.getHumidity() + AppConstants.UNIT_HUMIDITY;
                tvHumidity.setText(humidity);
            }

            if (weatherRealm.getTemp() != null) {
                String temp = weatherRealm.getTemp() + AppConstants.DEGREE_CELCIUS;
                tvTemp.setText(temp);
            }

            if (weatherRealm.getTempMax() != null) {
                String maxTemp = weatherRealm.getTempMax() + AppConstants.DEGREE_CELCIUS;
                tvMaxTemp.setText(maxTemp);
            }

            if (weatherRealm.getTempMin() != null) {
                String minTemp = weatherRealm.getTempMin() + AppConstants.DEGREE_CELCIUS;
                tvMinTemp.setText(minTemp);
            }

            if (weatherRealm.getWindSpeed() != null) {
                String windSpeed = weatherRealm.getWindSpeed() + AppConstants.WIND_SPEED;
                tvWindSpeed.setText(windSpeed);
            } else {
                tvWindSpeed.setText(AppConstants.EMPTY);
            }

            if (!TextUtils.isEmpty(weatherRealm.getIcon())) {
                Picasso.with(WeatherActivity.this)
                        .load(getString(R.string.str_open_weather_image_url)
                                + weatherRealm.getIcon() + AppConstants.IMAGE_FORMAT)
                        .into(ivWeatherIcon);
            }
        }
    }

    @Override
    public void populateDefaultScreenOnUI() {
        llWeatherDetailsAvailable.setVisibility(View.GONE);
        tvWeatherDetailsNotAvailable.setVisibility(View.VISIBLE);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Private methods
    ///////////////////////////////////////////////////////////////////////////

    private void initUI() {

        placeAutocompleteFragment =
                (PlaceAutocompleteFragment) getFragmentManager()
                        .findFragmentById(R.id.place_autocomplete_fragment);

        placeAutocompleteFragment.setOnPlaceSelectedListener(this);
    }
}
