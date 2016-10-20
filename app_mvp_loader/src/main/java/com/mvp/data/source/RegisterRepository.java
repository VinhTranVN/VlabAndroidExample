package com.mvp.data.source;

/**
 * Created by Vinh.Tran on 9/26/16.
 */

public class RegisterRepository implements RegisterDataProvider {

    private static RegisterRepository INSTANCE = null;
    private RegisterDataProvider mDataProvider;

    public RegisterRepository(RegisterDataProvider dataProvider) {
        mDataProvider = dataProvider;
    }

    public static RegisterRepository getInstance(RegisterDataProvider dataProvider) {
        if (INSTANCE == null) {
            // inject remote or local data source here
            INSTANCE = new RegisterRepository(dataProvider);
        }
        return INSTANCE;
    }

    @Override
    public boolean registerPhoneNumber(String phoneNumber) {
        return mDataProvider.registerPhoneNumber(phoneNumber);
    }
}
