package com.droid.us.myweatherapp.database;

import com.droid.us.myweatherapp.MyWeatherApplication;
import com.droid.us.myweatherapp.utility.LogUtility;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.droid.us.myweatherapp.utility.DBConstantUtility.DB_NAME;
import static com.droid.us.myweatherapp.utility.DBConstantUtility.SCHEMA_VERSION;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
public class RealmManager {

    private final String TAG = RealmManager.class.getName();

    public void configureRealm() {

        LogUtility.d(TAG, "Configuring Realm");

        Realm.init(MyWeatherApplication.getInstance());

        Realm.setDefaultConfiguration(getRealmConfig());
    }

    private RealmConfiguration getRealmConfig() {
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(SCHEMA_VERSION);

        return builder.build();
    }
}
