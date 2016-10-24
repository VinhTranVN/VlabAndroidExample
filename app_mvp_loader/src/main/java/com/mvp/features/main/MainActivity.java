package com.mvp.features.main;

import android.content.res.Configuration;
import android.os.Bundle;

import com.mvp.R;
import com.mvp.features.common.BaseActivity;
import com.mvp.loaders.MainLoader;
import com.mvp.utils.ActivityUtils;

/**
 * A login screen that offers login via phone number
 */
public class MainActivity extends BaseActivity {

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (mainFragment == null) {
            // Create the fragment
            mainFragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mainFragment, R.id.fragment_container);
        }

        // Create the presenter
        mPresenter = new MainPresenter(mainFragment, getSupportLoaderManager(), new MainLoader(this));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        System.out.println(">>> MainActivity -> onConfigurationChanged : ");
    }
}

