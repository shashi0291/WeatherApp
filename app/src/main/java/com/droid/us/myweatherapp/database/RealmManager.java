/*
 * File Name : RealmManager.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.database;

import com.droid.us.myweatherapp.MyWeatherApplication;
import com.droid.us.myweatherapp.utility.LogUtility;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.droid.us.myweatherapp.utility.DBConstantUtility.DB_NAME;
import static com.droid.us.myweatherapp.utility.DBConstantUtility.SCHEMA_VERSION;

/**
 * Class responsible to initialize the Realm DB configuration
 *
 * @author Shashi Pal
 */
public class RealmManager {

    private final String TAG = RealmManager.class.getName();

    public void configureRealm() {

        LogUtility.d(TAG, "Configuring Realm");

        Realm.init(MyWeatherApplication.getInstance());

        Realm.setDefaultConfiguration(getRealmConfig());
    }

    /**
     * Private method to set the realm related configuration
     * @return RealmConfig object
     */
    private RealmConfiguration getRealmConfig() {
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(SCHEMA_VERSION);

        return builder.build();
    }
}
