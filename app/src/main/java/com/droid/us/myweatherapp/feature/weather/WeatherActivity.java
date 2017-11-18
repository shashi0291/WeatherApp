package com.droid.us.myweatherapp.feature.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.droid.us.myweatherapp.R;
import com.droid.us.myweatherapp.feature.AppBaseActivity;

public class WeatherActivity extends AppBaseActivity {

    ///////////////////////////////////////////////////////////////////////////
    // Global members
    ///////////////////////////////////////////////////////////////////////////

    private WeatherContractor.Presenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void onCreateAfterBinding(@Nullable Bundle savedInstanceState) {
        mPresenter = new WeatherPresenter();
    }
}
