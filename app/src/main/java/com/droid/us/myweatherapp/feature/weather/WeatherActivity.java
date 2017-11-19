package com.droid.us.myweatherapp.feature.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.droid.us.myweatherapp.R;
import com.droid.us.myweatherapp.feature.AppBaseActivity;
import com.droid.us.myweatherapp.utility.LogUtility;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import butterknife.BindView;

public class WeatherActivity extends AppBaseActivity implements WeatherContractor.View,
        PlaceSelectionListener {

    ///////////////////////////////////////////////////////////////////////////
    // Global members
    ///////////////////////////////////////////////////////////////////////////

    private static final String TAG = WeatherActivity.class.getSimpleName();

    private WeatherContractor.Presenter mPresenter;

    PlaceAutocompleteFragment placeAutocompleteFragment;


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
    }

    @Override
    public void onError(Status status) {

    }

    ///////////////////////////////////////////////////////////////////////////
    // Private methods
    ///////////////////////////////////////////////////////////////////////////

    private void initUI() {

        placeAutocompleteFragment =
                (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        placeAutocompleteFragment.setOnPlaceSelectedListener(this);
    }
}
