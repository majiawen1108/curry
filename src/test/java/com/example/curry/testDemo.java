package com.example.curry;

import com.example.curry.model.TestUser;
import com.example.curry.test.Impl.*;
import com.example.curry.test.UserInfoService;
import com.example.curry.test.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @author jw.ma
 * @title: testDemo
 * @description: TODO
 * @date 2022/6/16 14:47
 */

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class testDemo {


    @Test
    public void test01(){
        UserInfoService userInfoService01 = UserUtils.getUserInfoServiceByType("1");
        TestUser testUser01 = userInfoService01.getTestUser();
        log.info(testUser01.toString());

        UserInfoService userInfoService02 = UserUtils.getUserInfoServiceByType("2");
        TestUser testUser02 = userInfoService02.getTestUser();
        log.info(testUser02.toString());
    }

    @Test
    public void test02(){
        UserServiceMore userServiceMore01 = new UserServiceMore("1");
        TestUser userInfoMore01 = userServiceMore01.getUserInfoMore();
        log.info("01最终结果是：{}",userInfoMore01);

        UserServiceMore userServiceMore02 = new UserServiceMore("2");
        TestUser userInfoMore02 = userServiceMore02.getUserInfoMore();
        log.info("02最终结果是：{}",userInfoMore02);
    }

    @Test
    public void test03(){
        UserInfoService userInfoService = new UserServiceImpl01();
        TestUser testUser00 = userInfoService.getTestUser();

        log.info("00最终结果是：{}",testUser00);
        UserInfoService userInfoService01 = new UserDecorator01(userInfoService);
        TestUser testUser01 = userInfoService01.getTestUser();

        log.info("01最终结果是：{}",testUser01);
        UserInfoService userInfoService02 = new UserDecorator02(userInfoService);
        TestUser testUser02 = userInfoService02.getTestUser();

        log.info("02最终结果是：{}",testUser02);


    }

    @Test
    public void test04(){
        Bags bigBag = new Bags("大袋子");
        Bags smallBag = new Bags("小袋子");
        Bags middleBag = new Bags("中袋子");

        Things things;
        things = new Things("娃哈哈", BigDecimal.valueOf(5.5),5);
        smallBag.add(things);
        things = new Things("冰红茶", BigDecimal.valueOf(3.5),5);
        smallBag.add(things);
        things = new Things("纯牛奶", BigDecimal.valueOf(4.5),9);
        middleBag.add(things);
        things = new Things("矿泉水", BigDecimal.valueOf(2.5),5);
        middleBag.add(things);
        bigBag.add(smallBag);
        bigBag.add(middleBag);
        log.info("一共买了：");
        bigBag.show();
        log.info("需要支付的总价是：{}",bigBag.getPrice());
    }
}
