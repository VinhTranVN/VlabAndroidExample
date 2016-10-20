/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mvp.loaders;

import android.content.Context;

import com.mvp.AbstractAsyncTaskLoader;
import com.mvp.Injection;
import com.mvp.data.source.LoginRepository;
import com.mvp.model.UserInfo;

public class LoginLoader extends AbstractAsyncTaskLoader<UserInfo> {

    private LoginRepository mRepository;

    private String mUserName = "";
    private String mPassword = "";

    public LoginLoader(Context context) {
        super(context);
        // provide a register repository
        mRepository = Injection.provideLoginRepository(context);
    }

    @Override
    public UserInfo loadInBackground() {
        return mRepository.login(mUserName, mPassword);
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
