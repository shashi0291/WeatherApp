/*
 * File Name : AppBaseActivity.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.feature;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * This class is created keeping in mind that, all activities needs not to be written from scratch.
 * It provides the basic implementation of any activity.
 *
 * @author Shashi Pal
 */
public abstract class AppBaseActivity extends AppCompatActivity {

    @Nullable
    private Unbinder mButterKnifeUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        // get butterknife initialized
        mButterKnifeUnbinder = ButterKnife.bind(this);

        onCreateAfterBinding(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // unbind the butterknife
        if (mButterKnifeUnbinder != null) {
            mButterKnifeUnbinder.unbind();
        }
    }

    // get the layout id from the hosting activity
    protected abstract int getLayoutId();

    /**
     * This method is a replacement for the {@link #onCreate(Bundle)}. This method will be invoked
     * only after the {@link ButterKnife#bind(Activity)} call, hence avoiding any access of UI
     * element before initializing of the same.
     * <p>
     * Once this method's implementation is completed
     * {@link com.droid.us.myweatherapp.feature.weather.WeatherContractor.Presenter}
     * will be invoked. Hence make sure your presenter is initialized at this point of time.
     * </p>
     *
     * @param savedInstanceState bundle which can be stored.
     */
    protected abstract void onCreateAfterBinding(@Nullable Bundle savedInstanceState);
}
