package com.example.curry.test.constant;

/**
 * @author jw.ma
 * @title: NumberEnum
 * @description: TODO
 * @date 2022/6/16 14:38
 */
public enum NumberEnum {
    ONE("1","一"),
    TWO("2","二");

    private String key;

    private String value;

    NumberEnum(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey(){
        return key;
    }
}
