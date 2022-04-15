package com.example.curry.model;

import lombok.Data;

/**
 * @author jw.ma
 * @title: TemplateIinfo
 * @description: 模板配置信息
 * @date 2022/1/30 09:55
 */
@Data
public class TemplateIinfo {
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 列字段码值
     */
    private String columnCode;
    /**
     * 列字段名称
     */
    private String columnValue;
    /**
     * 列宽
     */
    private Integer columnWidth;
    /**
     * 排序
     */
    private Integer sortNo;
    /**
     * 是否过滤
     */
    private String isFilter;
}
