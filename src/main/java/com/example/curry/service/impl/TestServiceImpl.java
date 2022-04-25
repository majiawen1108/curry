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

    @Override
    public List<TestUser> queryUserListWithPage(RequestParms<TestUser> testUserRequestParms) {
         return testMapper.queryUserList(testUserRequestParms.getData());
    }

    /**
     * 测试 @Data 和 @Builder 共存问题
     * @param args
     */
    public static void main(String[] args) {
        TestUser build = TestUser.builder().username("userName").address("address").id("123").phoneNumber("132-1231-1321").build();
        System.out.println(build);
        TestUser testUser = new TestUser();
        testUser.setUsername("userName1");
        testUser.setAddress("address1");
        testUser.setPhoneNumber("123");
        System.out.println(testUser);
    }
}
