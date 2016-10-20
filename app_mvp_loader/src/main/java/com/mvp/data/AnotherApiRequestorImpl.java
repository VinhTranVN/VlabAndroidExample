package com.mvp.data;

import android.content.Context;

import com.mvp.R;

/**
 * Created by Vinh.Tran on 10/20/16.
 */
public class AnotherApiRequestorImpl implements ApiRequestor {

    private static AnotherApiRequestorImpl sInstance;

    // TODO
    private final Object mFoo = null;

    public static AnotherApiRequestorImpl getInstance(Context context) {
        if(sInstance == null){
            sInstance = new AnotherApiRequestorImpl(context.getString(R.string.base_url));
        }
        return sInstance;
    }

    private AnotherApiRequestorImpl(String baseUrl) {
        // TODO init Foo object

    }

    /**
     * return an Api implementation object
     * @param apiService
     * @param <T>
     * @return T : an Api implementation object
     */
    @Override
    public <T> T createApiRequest(Class<T> apiService) {
        // TODO
        return null;
    }
}
