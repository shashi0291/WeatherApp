/*
 * File Name : WeatherPresenter.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.feature.weather;

import android.text.TextUtils;

import com.droid.us.myweatherapp.database.mapper.ServerToDBMapper;
import com.droid.us.myweatherapp.database.model_db.WeatherRealm;
import com.droid.us.myweatherapp.network.callback.WeatherNWCallback;
import com.droid.us.myweatherapp.network.server_model.Parent;
import com.droid.us.myweatherapp.rx.RxError;
import com.droid.us.myweatherapp.utility.LogUtility;
import com.google.android.gms.maps.model.LatLng;

import java.lang.ref.WeakReference;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter class for the {@link WeatherActivity}
 * This class is responsible to carry out the interaction between {@link WeatherActivity} and
 * {@link WeatherModel}
 *
 * @author Shashi Pal
 */
class WeatherPresenter implements WeatherContractor.Presenter {

    ///////////////////////////////////////////////////////////////////////////
    // Global members
    ///////////////////////////////////////////////////////////////////////////

    private final String TAG = WeatherPresenter.class.getName();

    private final WeakReference<WeatherContractor.View> mView;

    private final WeatherContractor.Model mModel;

    private final CompositeDisposable mDisposable;

    WeatherPresenter(WeatherContractor.View view) {

        mView = new WeakReference<>(view);

        mModel = new WeatherModel(this);

        mDisposable = new CompositeDisposable();

        // start searching for previously searched city as soon as the presenter is loaded into memory
        fetchLastSearchedCity();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Method responsible to initiate the search mechanism.
     * 1. Displays progress bar
     * 2. Requests model class to perform the NW layer operation
     * 3. Once data is available, saves the data into local DB (as per the requirement, the last
     * searched city result shold be displayed to user)
     * @param latLng lat-long of the searched location
     */
    @Override
    public void fetchCurrentWeatherDetail(LatLng latLng) {

        // show progress dialog
        if (mView.get() != null) {
            mView.get().showProgressBar();
        }

        // initiate the NW operation
        mModel.getWeatherDetail(latLng, new WeatherNWCallback() {
            @Override
            public void onSuccess(Parent weather) {

                LogUtility.d(TAG, "Successfully data fetched from Server");

                if (mView.get() != null) {
                    // set the country name (might be needed in future)
                    weather.setCountryName(mView.get().getCountryName());
                    // set the city name
                    weather.setCityName(mView.get().getCityName());
                    // transform the server result into DB insertable result
                    WeatherRealm weatherRealm = new ServerToDBMapper().map(weather);
                    // populate the result on UI
                    mView.get().populateDataOnUI(weatherRealm);
                    // save the data into DB
                    saveDataInDB(weatherRealm);
                    // hide the progress dialog
                    mView.get().dismissProgressBar();
                }
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                LogUtility.e(TAG, "Failed to fetch data from Server: " + throwable.getMessage());
                if (mView.get() != null) {
                    // simply dismiss the dialog
                    mView.get().dismissProgressBar();
                }
            }
        });

    }

    /**
     * Request the model to perform the DB insert operation
     * @param weatherRealm DB compatible object
     */
    public void saveDataInDB(WeatherRealm weatherRealm) {
        mDisposable.add(mModel.saveDataInDB(weatherRealm)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherRealm>() {
                    @Override
                    public void accept(@NonNull WeatherRealm weatherRealm) throws Exception {
                        LogUtility.d(TAG, "DB insertion successful");
                    }
                }, new RxError(TAG) {
                    @Override
                    public void acceptNow(@io.reactivex.annotations.NonNull
                                                  Throwable throwable) throws Exception {
                        if (mView.get() != null) {
                            mView.get().dismissProgressBar();
                        }
                    }
                }));
    }


    /**
     * Method that requests model to carry out a DB search operation. If data is present in the
     * DB, presenter sends it to View to populate it.
     */
    @Override
    public void fetchLastSearchedCity() {
        if (mView.get() != null) {
            mView.get().showProgressBar();
        }
        // ask model to fetch the previously stored data
        mDisposable.add(mModel.fetchLastSearchedCityData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherRealm>() {
                    @Override
                    public void accept(@NonNull WeatherRealm weatherRealm) throws Exception {
                        if (mView.get() != null) {
                            // if data is empty, populate the default UI
                            if (weatherRealm != null && !TextUtils.isEmpty(weatherRealm.getCityName())) {
                                mView.get().populateDataOnUI(weatherRealm);
                            } else {
                                mView.get().populateDefaultScreenOnUI();
                            }
                            mView.get().dismissProgressBar();
                        }
                    }
                }, new RxError(TAG) {
                    @Override
                    public void acceptNow(@io.reactivex.annotations.NonNull
                                                  Throwable throwable) throws Exception {
                        if (mView.get() != null) {
                            mView.get().dismissProgressBar();
                        }
                    }
                }));
    }


}
