package com.example.curry.utils;

import lombok.Data;

/**
 * @author jw.ma
 * @title: RequestParms
 * @description: 分页传参body
 * @date 2022/4/15 16:00
 */
@Data
public class RequestParms<T> {
    private T data;
    private int pageNum;
    private int pageSize;

    public RequestParms(T t) {
        this.data = t;
    }

    public RequestParms() {
    }
}
