package com.droid.us.myweatherapp.feature;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TODO: Provide a brief summary of the class in one or two lines.
 *
 * @author TODO: Add your name when contributing to this class.
 */
public abstract class AppBaseActivity extends AppCompatActivity {

    @Nullable
    private Unbinder mButterKnifeUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

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

    protected abstract int getLayoutId();

    protected abstract void onCreateAfterBinding(@Nullable Bundle savedInstanceState);
}
