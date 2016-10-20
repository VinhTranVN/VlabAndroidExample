package com.mvp.data;

/**
 * Created by Vinh.Tran on 10/20/16.
 */

public interface ApiRequestor {
    /**
     * create an Api Object to request data from server
     * @param apiService
     * @param <T>
     * @return instance Api Object
     */
    <T> T createApiRequest(Class<T> apiService);
}
