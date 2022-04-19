package com.example.curry.model;

import com.github.pagehelper.Page;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @author jw.ma
 * @title: PageListResult
 * @description: TODO
 * @date 2022/4/18 10:58
 */
@Data
public class PageListResult<E> extends R<List<E>>{
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 每页显示的总条数
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private Integer totalNum;
    /**
     * 是否有下一页
     */
    private Integer isMore;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 开始索引
     */
    private Integer startIndex;
    /**
     * 分页结果
     */
//    private T data;

    public PageListResult(List<E> t) {
        super(t,true,"success");
        if (t == null) {
            this.totalNum = 0;
            this.pageSize = 0;
            this.currentPage = 0;
        } else {
            this.totalNum = t.size();
            this.pageSize = t.size();
            this.currentPage = 0;
        }
    }
    public PageListResult() {
    }
}
