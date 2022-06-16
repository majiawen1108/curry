package com.example.curry.test.Impl;

import com.example.curry.model.TestUser;
import com.example.curry.test.UserInfoService;

/**
 * @author jw.ma
 * @title: UserServiceImpl01
 * @description: TODO
 * @date 2022/6/16 14:33
 */
public class UserServiceImpl01 implements UserInfoService {
    @Override
    public TestUser getTestUser() {
        TestUser testUser01 = TestUser.builder().id("0001").username("实现类一号").phoneNumber("0123456789").address("01号住宅").build();

        return testUser01;
    }
}
