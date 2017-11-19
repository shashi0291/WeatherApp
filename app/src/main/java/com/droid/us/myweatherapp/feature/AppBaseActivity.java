/*
 * File Name : AppBaseActivity.java
 * Project : WeatherApp
 * Created by : Shashi
 * Date : November 19, 2017
 */
package com.droid.us.myweatherapp.feature;

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

        if (mButterKnifeUnbinder != null) {
            mButterKnifeUnbinder.unbind();
        }
    }

    // get the layout id from the hosting activity
    protected abstract int getLayoutId();

    // to be implemented by the activity (teh oncreate is already called)
    protected abstract void onCreateAfterBinding(@Nullable Bundle savedInstanceState);
}
