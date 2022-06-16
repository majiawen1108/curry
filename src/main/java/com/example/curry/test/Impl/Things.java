package com.example.curry.test.Impl;

import com.example.curry.test.Shop;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @author jw.ma
 * @title: Things
 * @description: TODO
 * @date 2022/6/16 16:19
 */
@Slf4j
public class Things implements Shop {

    private String name;

    private BigDecimal price;

    private int num;

    public Things(String name,BigDecimal price,int num){
        this.name = name;
        this.price = price;
        this.num = num;
    }


    @Override
    public BigDecimal getPrice() {
        return price.multiply(BigDecimal.valueOf(num));
    }

    @Override
    public void show() {
        log.info(name + "(数量：" + num + "，单价：" + price + "元)");
    }
}
