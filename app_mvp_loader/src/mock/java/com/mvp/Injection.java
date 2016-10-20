/*
 * Copyright (C) 2015 The Android Open Source Project
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

package com.mvp;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mvp.data.ApiRequestor;
import com.mvp.data.RetrofitApiRequestorImpl;
import com.mvp.data.api.GitHubApi;
import com.mvp.data.source.LoginRepository;
import com.mvp.data.source.RegisterDataProvider;
import com.mvp.data.source.RegisterRepository;
import com.mvp.data.source.dummy.RegisterDummyDataProviderImpl;
import com.mvp.data.source.remote.LoginRemoteDataProviderImpl;

import static com.mvp.utils.ValidatorUtil.checkNotNull;

/**
 * Enables injection of mock implementations for
 * {@link RegisterDataProvider} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 */
public class Injection {

    public static RegisterRepository provideRegisterRepository(@NonNull Context context) {
        checkNotNull(context);
        // determine get data source from server or local
        return RegisterRepository.getInstance(new RegisterDummyDataProviderImpl());
    }

    public static LoginRepository provideLoginRepository(@NonNull Context context) {
        checkNotNull(context);
        // determine get data source from server or local
        return LoginRepository.getInstance(
                new LoginRemoteDataProviderImpl(getApiRequestor(context).createApiRequest(GitHubApi.class))
        );
    }

    private static ApiRequestor getApiRequestor(Context context){
        // TODO may change api requestor is Retrofit or another 3rd party in the future
        return RetrofitApiRequestorImpl.getInstance(context);
    }

}
