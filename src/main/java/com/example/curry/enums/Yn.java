package com.example.curry.enums;

/**
 * @author jw.ma
 * @title: Yn
 * @description: TODO
 * @date 2022/1/30 10:25
 */
public enum Yn implements BaseEnum<String,String> {
    YES("1","是"),
    NO("0","否"),
    ;

    private String key;

    private String value;

    Yn(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String key() {
        return null;
    }

    @Override
    public String value() {
        return null;
    }
}
