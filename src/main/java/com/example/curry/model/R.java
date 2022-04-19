package com.example.curry.model;

import lombok.Data;

@Data
public class R<T> {

    private String msg;

    private boolean status;

    private T data;

    public R(String msg, boolean status) {
        this.msg = msg;
        this.status = status;
    }

    public R(T data, boolean status, String msg){
        this.data = data;
        this.status = status;
        this.msg = msg;
    }

    public R() {
    }

    public static <T> R<T> result(T t, boolean status, String msg){
        return new R(t,status,msg);
    }

    public static <T> R<T> success(String s) {
        return new R(s,true  );
    }

    public static <T> R<T> fail(String toString) {
        return new R<>(toString,false);
    }
}
