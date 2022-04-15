package com.example.curry.enums;

/**
 * @author jw.ma
 * @title: BaseEnum
 * @description: TODO
 * @date 2022/1/30 10:23
 */
public interface BaseEnum<T,U> {
    T key();
    U value();
}
