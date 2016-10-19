package com.mvp.features.register;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mvp.loaders.RegisterLoader;

import static com.mvp.utils.ValidatorUtil.checkNotNull;

/**
 * Created by Vinh.Tran on 9/26/16.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private static final int REGISTER_LOADER_ID = 0;

    private final RegisterLoader mRegisterLoader;
    private final LoaderManager mLoaderManager;
    private final RegisterContract.View mRegisterView;

    public RegisterPresenter(
            RegisterLoader loader,
            LoaderManager loaderManager,
            RegisterContract.View registerView) {

        mRegisterLoader = loader;
        mLoaderManager = checkNotNull(loaderManager, "loader manager cannot be null");
        mRegisterView = checkNotNull(registerView, "RegisterView cannot be null!");

        mRegisterView.setPresenter(this);
    }

    @Override
    public void register(String phoneNumber) {
        mRegisterLoader.setPhoneNumber(phoneNumber);

        mLoaderManager.restartLoader(REGISTER_LOADER_ID, null, new LoaderManager.LoaderCallbacks<Boolean>() {
            @Override
            public Loader<Boolean> onCreateLoader(int id, Bundle args) {
                // show loading
                mRegisterView.setLoadingIndicator(true);
                return mRegisterLoader;
            }

            @Override
            public void onLoadFinished(Loader<Boolean> loader, Boolean isRegisterSuccess) {
                System.out.println(">>> RegisterPresenter -> onLoadFinished : " + isRegisterSuccess);
                // hide loading
                mRegisterView.setLoadingIndicator(false);
                if(isRegisterSuccess){
                    onRegisterSuccess();
                } else {
                    onRegisterFailure();
                }

                mLoaderManager.destroyLoader(REGISTER_LOADER_ID);
            }

            @Override
            public void onLoaderReset(Loader<Boolean> loader) {

            }
        });
    }

    @Override
    public void onRegisterSuccess() {
        mRegisterView.showMainScreen();
    }

    @Override
    public void onRegisterFailure() {
        mRegisterView.showToastMsg("Register failed!!!");
    }

    @Override
    public void start() {
        // TODO do nothing at the moment
    }
}
