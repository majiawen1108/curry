package com.example.curry.test.Impl;

import com.example.curry.model.TestUser;
import com.example.curry.test.UserInfoService;

/**
 * @author jw.ma
 * @title: UserDecorator01
 * @description: TODO
 * @date 2022/6/16 15:34
 */
public class UserDecorator01 extends UserServiceMiddle{
    public UserDecorator01(UserInfoService u) {
        super(u);
    }

    @Override
    public TestUser getTestUser(){
        TestUser testUser = super.userInfoService.getTestUser();
        testUser.setUsername("装饰器01");
        return testUser;
    }
}
