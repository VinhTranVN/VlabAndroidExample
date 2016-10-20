package com.mvp.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvp.R;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vinh.Tran on 10/20/16.
 */
public class RetrofitApiRequestorImpl implements ApiRequestor {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static RetrofitApiRequestorImpl sInstance;

    // TODO we may change Retrofit by another 3rd part ro request API in the future
    private final Retrofit mRetrofit;

    public static RetrofitApiRequestorImpl getInstance(Context context) {
        if(sInstance == null){
            sInstance = new RetrofitApiRequestorImpl(context.getString(R.string.base_url));
        }
        return sInstance;
    }

    private RetrofitApiRequestorImpl(String baseUrl) {
        // TODO
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.interceptors().add(logging);

        OkHttpClient okHttpClient = builder.build();

        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_TIME_FORMAT)
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    /**
     * return an Api implementation object
     * @param apiService
     * @param <T>
     * @return T : an Api implementation object
     */
    @Override
    public <T> T createApiRequest(Class<T> apiService) {
        return sInstance.mRetrofit.create(apiService);
    }
}
