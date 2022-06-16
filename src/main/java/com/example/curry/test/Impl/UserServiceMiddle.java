package com.example.curry.test.Impl;

import com.example.curry.model.TestUser;
import com.example.curry.test.UserInfoService;

/**
 * @author jw.ma
 * @title: UserServiceMiddle
 * @description: TODO
 * @date 2022/6/16 15:30
 */
public class UserServiceMiddle implements UserInfoService {

    UserInfoService userInfoService;

    public UserServiceMiddle(UserInfoService u){
        this.userInfoService = u;
    }

    @Override
    public TestUser getTestUser() {
        return userInfoService.getTestUser();
    }
}
