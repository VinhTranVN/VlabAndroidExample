package com.mvp.data.source.remote;

import android.util.Base64;

import com.mvp.data.api.GitHubApi;
import com.mvp.data.response.UserResponse;
import com.mvp.data.source.LoginDataProvider;
import com.mvp.model.UserInfo;

import retrofit2.Call;

/**
 * Created by Vinh.Tran on 10/20/16.
 */
public class LoginRemoteDataProviderImpl implements LoginDataProvider {

    private GitHubApi mApi;

    public LoginRemoteDataProviderImpl(GitHubApi api) {
        mApi = api;
    }

    @Override
    public UserInfo login(String userName, String password) {
        String authentication = userName + ":" + password;
        Call<UserResponse> login = mApi.login("Basic " + Base64.encodeToString(authentication.getBytes(), Base64.NO_WRAP));
        try {
            UserResponse userResponse = login.execute().body();
            return new UserInfo(userResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
