package com.example.curry.test.Impl;

import com.example.curry.model.TestUser;
import com.example.curry.test.UserInfoService;
import com.example.curry.test.UserUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jw.ma
 * @title: UserServiceMore
 * @description: TODO
 * @date 2022/6/16 14:53
 */
@Slf4j
public class UserServiceMore {

    private UserInfoService userInfoService;

    public UserServiceMore(String type){
        log.info("传入的type是：{}",type);
        userInfoService = UserUtils.getUserInfoServiceByType(type);
    }

    public TestUser getUserInfoMore(){
        TestUser testUser = userInfoService.getTestUser();
        log.info("原userInfo：{}",testUser);
        testUser.setId(testUser.getId()+"more");
        testUser.setUsername(testUser.getUsername()+"more");
        testUser.setAddress(testUser.getAddress()+"more");
        testUser.setPhoneNumber(testUser.getPhoneNumber()+"more");
        log.info("改变后的userInfo：{}",testUser);
        return testUser;
    }
}
