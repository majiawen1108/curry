package com.example.curry.test.Impl;

import com.example.curry.test.Shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jw.ma
 * @title: Bags
 * @description: TODO
 * @date 2022/6/16 16:18
 */
public class Bags implements Shop {

    private String name;

    private List<Shop> list = new ArrayList<>();

    public Bags(String name){
        this.name = name;
    }

    public void add(Shop shop){
        list.add(shop);
    }

    public void remove(Shop shop){
        list.remove(shop);
    }

    public Shop getChild(int number){
        return list.get(number);
    }
    @Override
    public BigDecimal getPrice() {
        BigDecimal price = BigDecimal.ZERO;
        for (Shop vo : list) {
            price = price.add(vo.getPrice());
        }
        return price;
    }

    @Override
    public void show() {
        for (Shop shop : list) {
            shop.show();
        }
    }
}
