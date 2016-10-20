package com.mvp.model;

import com.mvp.data.response.UserResponse;

/**
 * Created by Vinh.Tran on 10/20/16.
 */

public class UserInfo {
    int id;
    String userName;

    public UserInfo(UserResponse userResponse) throws Exception {
        this.id = userResponse.getId();
        this.userName = userResponse.getName();
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }
}
