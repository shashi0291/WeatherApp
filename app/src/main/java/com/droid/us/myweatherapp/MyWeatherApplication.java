package com.droid.us.myweatherapp;

import android.app.Application;

import com.droid.us.myweatherapp.database.RealmManager;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
public class MyWeatherApplication extends Application {

    /**
     * It holds the current instance of the class.
     */
    private static MyWeatherApplication instance;

    /**
     * @return Current instance of the class.
     */
    public static MyWeatherApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // configure realm
        new RealmManager().configureRealm();

        // initialize Stetho for debugging purpose
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }
}
