package com.example.curry.test.Impl;

import com.example.curry.model.TestUser;
import com.example.curry.test.UserInfoService;

/**
 * @author jw.ma
 * @title: UserServiceImpl01
 * @description: TODO
 * @date 2022/6/16 14:33
 */
public class UserServiceImpl02 implements UserInfoService {
    @Override
    public TestUser getTestUser() {
        TestUser testUser02 = TestUser.builder().id("0002").username("实现类二号").phoneNumber("0987654321").address("02号住宅").build();

        return testUser02;
    }
}
