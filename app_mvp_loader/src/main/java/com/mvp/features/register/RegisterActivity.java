package com.mvp.features.register;

import android.os.Bundle;

import com.mvp.R;
import com.mvp.features.common.BaseActivity;
import com.mvp.loaders.RegisterLoader;
import com.mvp.utils.ActivityUtils;

/**
 * A login screen that offers login via phone number
 */
public class RegisterActivity extends BaseActivity {

    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        RegisterFragment registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (registerFragment == null) {
            // Create the fragment
            registerFragment = RegisterFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), registerFragment, R.id.fragment_container);
        }

        // Create the presenter
        RegisterLoader registerLoader = new RegisterLoader(getApplicationContext(), "");

        mRegisterPresenter = new RegisterPresenter(
                registerLoader,
                getSupportLoaderManager(),
                registerFragment
        );
    }
}

