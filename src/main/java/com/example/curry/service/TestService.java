package com.example.curry.service;

import com.example.curry.model.TestUser;
import com.example.curry.utils.RequestParms;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author jw.ma
 * @title: TestService
 * @description: TODO
 * @date 2022/4/15 10:19
 */
public interface TestService {

    List<TestUser> queryUserList(TestUser testUser);

    TestUser queryUserById(String id);

    PageInfo<TestUser> queryUserListByPage(RequestParms<TestUser> testUserRequestParms);
}
