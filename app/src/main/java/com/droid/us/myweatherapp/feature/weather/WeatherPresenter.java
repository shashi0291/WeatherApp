package com.droid.us.myweatherapp.feature.weather;

import com.droid.us.myweatherapp.database.callback.WeatherDBCallback;
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
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
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

        fetchLastSearchedCity();
    }


    @Override
    public void fetchCurrentWeatherDetail(LatLng latLng) {

       mModel.getWeatherDetail(latLng, new WeatherNWCallback() {
           @Override
           public void onSuccess(Parent weather) {
               LogUtility.d(TAG, "Successfully data fetched from Server");
               if (mView.get() != null) {
                   weather.setCountryName(mView.get().getCountryName());
                   weather.setCityName(mView.get().getCityName());
                   WeatherRealm weatherRealm = new ServerToDBMapper().map(weather);
                   mView.get().populateDataOnUI(weatherRealm);
                   updateDB(weatherRealm);
               }
           }

           @Override
           public void onError(@NonNull Throwable throwable) {
               LogUtility.e(TAG, "Failed to fetch data from Server: " + throwable.getMessage());
           }
       });

    }

    public void updateDB(WeatherRealm weatherRealm) {

        mModel.updateDatabase(weatherRealm, new WeatherDBCallback() {

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(@NonNull Throwable throwable) {

            }
        });

    }

    @Override
    public void fetchLastSearchedCity() {
        mDisposable.add(mModel.fetchLastSearchedCityData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherRealm>() {
                    @Override
                    public void accept(@NonNull WeatherRealm weatherRealm) throws Exception {
                        if (mView.get() != null) {
                            if (weatherRealm != null) {
                                mView.get().populateDataOnUI(weatherRealm);
                            } else {
                                mView.get().populateDefaultScreenOnUI();
                            }
                        }
                    }
                }, new RxError(TAG)));
    }


}
