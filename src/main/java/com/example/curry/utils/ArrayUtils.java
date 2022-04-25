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
     * @param <T> 数组中参数类型
     * @return 新的数组集合
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

    /**
     * 拆分LIST集合
     *
     * @param resList 被拆分数组
     * @param count 按照多少数据一组进行拆分
     * @param <T> 泛型
     * @return 新的数组集合
     */
    public static <T> List<List<T>> split(List<T> resList, int count) {
        if (resList == null || count < 1) {
            return null;
        }
        List<List<T>> ret = new ArrayList<List<T>>();
        int size = resList.size();
        if (size <= count) {
            ret.add(resList);
        } else {
            int pre = size / count;
            int last = size % count;
            for (int i = 0; i < pre; i++) {
                List<T> itemList = new ArrayList<T>();
                for (int j = 0; j < count; j++) {
                    itemList.add(resList.get(i * count + j));
                }
                ret.add(itemList);
            }
            if (last > 0) {
                List<T> itemList = new ArrayList<T>();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * count + i));
                }
                ret.add(itemList);
            }
        }
        return ret;
    }
}
