package com.mvp.data.source;

import com.mvp.model.UserInfo;

/**
 * Created by Vinh.Tran on 9/26/16.
 */

public class LoginRepository implements LoginDataProvider {

    private static LoginRepository INSTANCE = null;
    private LoginDataProvider mDataProvider;

    public LoginRepository(LoginDataProvider dataProvider) {
        mDataProvider = dataProvider;
    }

    public static LoginRepository getInstance(LoginDataProvider dataPrvider) {
        if (INSTANCE == null) {
            // inject remote or local data source here
            INSTANCE = new LoginRepository(dataPrvider);
        }
        return INSTANCE;
    }

    @Override
    public UserInfo login(String userName, String password) {
        return mDataProvider.login(userName, password);
    }
}
