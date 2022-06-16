package com.example.curry.test;

import com.example.curry.test.Impl.UserServiceImpl01;
import com.example.curry.test.Impl.UserServiceImpl02;
import com.example.curry.test.constant.NumberEnum;

/**
 * @author jw.ma
 * @title: UserUtils
 * @description: TODO
 * @date 2022/6/16 14:36
 */
public class UserUtils {

    public static UserInfoService getUserInfoServiceByType(String type){
        if (NumberEnum.ONE.getKey().equals(type)){
            return new UserServiceImpl01();
        }else if (NumberEnum.TWO.getKey().equals(type)){
            return new UserServiceImpl02();
        }
        return null;
    }

}
