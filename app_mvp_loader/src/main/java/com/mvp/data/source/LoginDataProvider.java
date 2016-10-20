package com.mvp.data.source;

import com.mvp.model.UserInfo;

/**
 * Created by Vinh.Tran on 9/26/16.
 */

public interface LoginDataProvider {
    UserInfo login(String userName, String password);
}
