package com.droid.us.myweatherapp.feature.weather;

import com.droid.us.myweatherapp.MyWeatherApplication;
import com.droid.us.myweatherapp.R;
import com.droid.us.myweatherapp.database.callback.WeatherDBCallback;
import com.droid.us.myweatherapp.database.model_db.WeatherRealm;
import com.droid.us.myweatherapp.network.callback.WeatherNWCallback;
import com.droid.us.myweatherapp.network.datasource.NWWeatherDataSource;
import com.droid.us.myweatherapp.network.server_model.Parent;
import com.droid.us.myweatherapp.rx.RxError;
import com.droid.us.myweatherapp.utility.LogUtility;
import com.google.android.gms.maps.model.LatLng;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
class WeatherModel implements WeatherContractor.Model {

    private final String TAG = WeatherModel.class.getSimpleName();

    private final WeatherContractor.Presenter mWeatherPresenter;

    private final CompositeDisposable mDisposable;

    /**
     * Initialize the Model using the presenter, so that it can facilitate the data transfer
     * between them.
     *
     * @param weatherPresenter Presenter that contains all the business logic.
     */
    WeatherModel(WeatherContractor.Presenter weatherPresenter) {

        mWeatherPresenter = weatherPresenter;

        mDisposable = new CompositeDisposable();
    }


    @Override
    public void getWeatherDetail(LatLng latLng, final WeatherNWCallback weatherNWCallback) {

        mDisposable.add(fetchWeatherForCurrentDat(latLng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Parent>() {
                    @Override
                    public void accept(@NonNull Parent parent) throws Exception {
                        LogUtility.d(TAG, "Successfully data fetched from server in Weather Model");
                        if (weatherNWCallback != null && parent != null) {
                            weatherNWCallback.onSuccess(parent);
                        }

                    }
                }, new RxError(TAG) {
                    @Override
                    public void acceptNow(@NonNull Throwable throwable) throws Exception {
                        LogUtility.d(TAG, "### fetchAssetDeltaNetworkCall filter throwable : "
                                + throwable.getMessage());

                        if (weatherNWCallback != null) {
                            weatherNWCallback.onError(throwable);
                        }
                    }
                }));
    }

    @Override
    public Single<Parent> fetchWeatherForCurrentDat(LatLng latLng) {
        return new NWWeatherDataSource()
                .getCurrentWeatherDetail(latLng, MyWeatherApplication.getInstance().getString(
                        R.string.str_openweather_api_key))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public void updateDatabase(WeatherRealm weatherRealm, WeatherDBCallback callback) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            RealmResults<WeatherRealm> oldData = realm.where(WeatherRealm.class).findAll();
            if (oldData != null) {
                oldData.deleteAllFromRealm();
            }

            realm.copyToRealmOrUpdate(weatherRealm);
            realm.commitTransaction();
        } catch (Exception e) {
            if (realm.isInTransaction()) {
                realm.cancelTransaction();
            }
            throw e;
        } finally {
            if (!realm.isClosed()) {
                realm.close();
            }
        }
    }

    @Override
    public Flowable<WeatherRealm> saveDataInDB(final WeatherRealm weatherRealm) {
        return Flowable.just(true).map(new Function<Boolean, WeatherRealm>() {
            @Override
            public WeatherRealm apply(@NonNull Boolean aBoolean) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                try {
                    realm.beginTransaction();
                    RealmResults<WeatherRealm> oldData = realm.where(WeatherRealm.class).findAll();
                    if (oldData != null) {
                        oldData.deleteAllFromRealm();
                    }

                    realm.copyToRealmOrUpdate(weatherRealm);
                    realm.commitTransaction();
                } catch (Exception e) {
                    if (realm.isInTransaction()) {
                        realm.cancelTransaction();
                    }
                    throw e;
                } finally {
                    if (!realm.isClosed()) {
                        realm.close();
                    }
                }
                return weatherRealm;
            }

        });
    }

    @Override
    public Flowable<WeatherRealm> fetchLastSearchedCityData() {
        return Flowable.just(true).map(new Function<Boolean, WeatherRealm>() {
            @Override
            public WeatherRealm apply(@NonNull Boolean aBoolean) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                try {
                    realm.beginTransaction();

                    WeatherRealm weather = realm.where(WeatherRealm.class).findFirst();
                    WeatherRealm weatherRealm = new WeatherRealm();

                    if (weather != null) {

                        weatherRealm.setCountryName(weather.getCountryName());
                        weatherRealm.setCityName(weather.getCityName());
                        weatherRealm.setDate(weather.getDate());
                        weatherRealm.setHumidity(weather.getHumidity());
                        weatherRealm.setIcon(weather.getIcon());
                        weatherRealm.setTemp(weather.getTemp());
                        weatherRealm.setTempMin(weather.getTempMin());
                        weatherRealm.setTempMax(weather.getTempMax());
                        weatherRealm.setWeatherDescription(weather.getWeatherDescription());
                        weatherRealm.setWeatherOverview(weather.getWeatherOverview());


                    }
                    return weatherRealm;
                } catch (Throwable e) {
                    if (realm.isInTransaction()) {
                        realm.cancelTransaction();
                    }
                    throw e;
                } finally {
                    if (!realm.isClosed()) {
                        realm.close();
                    }
                }
            }
        });
    }
}
