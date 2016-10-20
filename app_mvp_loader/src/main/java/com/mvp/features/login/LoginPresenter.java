package com.mvp.features.login;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mvp.loaders.LoginLoader;
import com.mvp.model.UserInfo;

import static com.mvp.utils.ValidatorUtil.checkNotNull;

/**
 * Created by Vinh.Tran on 9/26/16.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final int LOGIN_LOADER_ID = 0;

    private Context mContext;
    private final LoaderManager mLoaderManager;
    private final LoginContract.View mLoginView;

    public LoginPresenter(
            Context context, LoaderManager loaderManager,
            LoginContract.View loginView) {
        mContext = context;

        mLoaderManager = checkNotNull(loaderManager, "loader manager cannot be null");
        mLoginView = checkNotNull(loginView, "LoginView cannot be null!");

        mLoginView.setPresenter(this);
    }


    @Override
    public void start() {
        // TODO do nothing at the moment, if get data at start, we will inject loader in constructor
    }

    @Override
    public void login(final String userName, final String password) {
        mLoaderManager.restartLoader(LOGIN_LOADER_ID, null, new LoaderManager.LoaderCallbacks<UserInfo>() {
            @Override
            public Loader<UserInfo> onCreateLoader(int id, Bundle args) {
                // show loading
                mLoginView.setLoadingIndicator(true);

                // set parameter for loader
                LoginLoader loginLoader = new LoginLoader(mContext);
                loginLoader.setUserName(userName);
                loginLoader.setPassword(password);

                return loginLoader;
            }

            @Override
            public void onLoadFinished(Loader<UserInfo> loader, UserInfo userInfo) {
                System.out.println(">>> LoginPresenter -> onLoadFinished : " + userInfo);
                // hide loading
                mLoginView.setLoadingIndicator(false);
                if(userInfo != null){
                    onLoginSuccess();
                } else {
                    onLoginFailure();
                }

                mLoaderManager.destroyLoader(LOGIN_LOADER_ID);
            }

            @Override
            public void onLoaderReset(Loader<UserInfo> loader) {

            }
        });
    }

    @Override
    public void onLoginSuccess() {
        mLoginView.showMainScreen();
    }

    @Override
    public void onLoginFailure() {
        mLoginView.showToastMsg("Login failed!!!");
    }
}
