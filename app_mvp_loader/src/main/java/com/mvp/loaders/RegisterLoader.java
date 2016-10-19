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
import com.mvp.data.source.RegisterRepository;

public class RegisterLoader extends AbstractAsyncTaskLoader<Boolean> {

    private RegisterRepository mRepository;
    private String mPhoneNumber;

    public RegisterLoader(Context context, String phoneNumber) {
        super(context);

        mPhoneNumber = phoneNumber;
        // provide a register repository
        mRepository = Injection.provideRegisterRepository(context);
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    @Override
    public Boolean loadInBackground() {
        return mRepository.registerPhoneNumber(mPhoneNumber);
    }
}
