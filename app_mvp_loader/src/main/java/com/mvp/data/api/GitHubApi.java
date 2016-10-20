package com.mvp.data.api;

import com.mvp.data.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by Vinh.Tran on 10/20/16.
 */

public interface GitHubApi {
    @GET("user")
    Call<UserResponse> login(@Header("Authorization") String authorization);
}
