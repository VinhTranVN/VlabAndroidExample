package com.mvp.features.login;

import android.os.Bundle;

import com.mvp.R;
import com.mvp.features.common.BaseActivity;
import com.mvp.utils.ActivityUtils;

/**
 * A login screen that offers login via phone number
 */
public class LoginActivity extends BaseActivity {

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            // Create the fragment
            fragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
        }

        // Create the presenter, inject Fragment View into it
        mPresenter = new LoginPresenter(
                // login loader
                this, getSupportLoaderManager(), // loader manager
                fragment // View
        );
    }
}

