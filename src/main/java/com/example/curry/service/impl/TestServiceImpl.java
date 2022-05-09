package com.example.curry.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.curry.mapper.TestMapper;
import com.example.curry.model.TestUser;
import com.example.curry.service.TestService;
import com.example.curry.utils.RequestParms;
import com.example.curry.utils.redis.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jw.ma
 * @title: TestServiceImpl
 * @description: TODO
 * @date 2022/4/15 10:20
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<TestUser> queryUserList(TestUser testUser) {
        List<TestUser> testUsers = testMapper.queryUserList(testUser);
        if (testUsers.size() > 0){
            redisUtil.set(testUser.getId(), JSON.toJSONString(testUsers));
        }
        log.info(redisUtil.get(testUser.getId()));
        return testUsers;
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

    @Override
    public void testFiFo1() {
        testFiFo();
    }


    public void testFiFo() {

        List<String> strings = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            new thred1("1000",strings,String.valueOf(i)).start();
        }

        for (int i = 0; i < 50; i++) {
            new thred2("1000").start();
        }

    }


    class thred1 implements Runnable{

        private String key;
        private List<String> value;
        private String va;

        public thred1(String key, List<String> value,String va) {
            this.key = key;
            this.value = value;
            this.va = va;
        }

        @Override
        public void run() {
            redisUtil.rpush(key,va);
        }

        public void start(){
            Thread thread = new Thread(this, Thread.currentThread().getName());
            thread.start();
        }
    }

    class thred2 implements Runnable{

        private String key;


        public thred2(String key) {
            this.key = key;
        }

        @Override
        public void run() {
            String lpop = redisUtil.lpop(key);
            log.info("取出====" + lpop);
        }

        public void start(){
            Thread thread = new Thread(this, Thread.currentThread().getName());
            thread.start();
        }
    }


    /**
     * 测试 @Data 和 @Builder 共存问题
     * @param args
     */
    public static void main(String[] args) {
//        TestUser build = TestUser.builder().username("userName").address("address").id("123").phoneNumber("132-1231-1321").build();
//        System.out.println(build);
//        TestUser testUser = new TestUser();
//        testUser.setUsername("userName1");
//        testUser.setAddress("address1");
//        testUser.setPhoneNumber("123");
//        System.out.println(testUser);
    }
}
