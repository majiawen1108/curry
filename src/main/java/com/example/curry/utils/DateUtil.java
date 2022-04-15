package com.example.curry.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jw.ma
 * @title: DateUtil
 * @description: TODO
 * @date 2022/1/30 11:00
 */
public class DateUtil {
    public static String format(Date date, String patten){
        DateFormat format = new SimpleDateFormat(patten);
        return format.format(date);
    }
}
