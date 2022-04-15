package com.example.curry.mapper;

import com.example.curry.model.TestUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jw.ma
 * @title: testMapper
 * @description: TODO
 * @date 2022/4/15 10:16
 */
@Repository
public interface TestMapper {

    public List<TestUser> queryUserList(TestUser testUser);

    public TestUser queryUserById(String id);
}
