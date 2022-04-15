package com.example.curry.service.impl;

import com.example.curry.mapper.TestMapper;
import com.example.curry.model.TestUser;
import com.example.curry.service.TestService;
import com.example.curry.utils.RequestParms;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jw.ma
 * @title: TestServiceImpl
 * @description: TODO
 * @date 2022/4/15 10:20
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<TestUser> queryUserList(TestUser testUser) {
        return testMapper.queryUserList(testUser);
    }

    @Override
    public TestUser queryUserById(String id) {
        return testMapper.queryUserById(id);
    }

    @Override
    public PageInfo<TestUser> queryUserListByPage(RequestParms<TestUser> testUserRequestParms) {
        PageHelper.startPage(testUserRequestParms.getPageNum(),testUserRequestParms.getPageSize());
        List<TestUser> testUsers = testMapper.queryUserList(testUserRequestParms.getData());
        PageInfo<TestUser> listPageInfo = new PageInfo<>(testUsers);
        return listPageInfo;
    }
}
