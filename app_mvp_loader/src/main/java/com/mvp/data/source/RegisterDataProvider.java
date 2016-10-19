package com.mvp.data.source;

/**
 * Created by Vinh.Tran on 9/26/16.
 */

public interface RegisterDataProvider {
    /**
     * @param phoneNumber
     * @return true : register success, false for other wise
     */
    boolean registerPhoneNumber(String phoneNumber);
}
