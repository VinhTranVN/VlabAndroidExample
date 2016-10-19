package com.mvp.data.source.dummy;

import com.mvp.data.source.RegisterDataProvider;

/**
 * Created by Vinh.Tran on 9/26/16.
 */
public class RegisterDummyDataProviderImpl implements RegisterDataProvider {
    @Override
    public boolean registerPhoneNumber(String phoneNumber) {
        // TODO dummy call API, delay 2 seconds
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(phoneNumber.equals("123456")){
            return true;
        }
        return false;
    }
}
