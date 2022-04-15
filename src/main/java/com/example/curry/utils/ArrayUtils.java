package com.example.curry.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jw.ma
 * @title: splitArray
 * @description: TODO
 * @date 2021/11/26 12:31
 */
@Slf4j
public class ArrayUtils {

    /**
     * 拆分集合
     *
     * @param lists 被拆分数组
     * @param splitSize 拆分份数
     * @param <T> 新的数组集合
     * @return
     */
    private static  <T> List<List<T>> spliceArrays(List<T> lists, int splitSize) {
        if (lists == null || splitSize < 1) {
            return null;
        }
        int totalSize = lists.size();
        int count = (totalSize % splitSize == 0) ? (totalSize / splitSize) : (totalSize / splitSize + 1);
        List<List<T>> rows = new ArrayList<>();
        for (int i = 0; i < splitSize; i++) {
            List<T> cols = lists.subList(i * count, (i == count - 1) ? totalSize : count * (i + 1));
            rows.add(cols);
            log.info("数组"+i+"为：" + cols.toString());
        }
        return rows;
    }
}
