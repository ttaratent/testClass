package com.sqb.designpattern.decorator;

// 定义被装饰者，被装饰者初始状态有些自己的装饰
public class Person implements Human {
    
    public void wearClothes() {
        System.out.println("穿什么呢...");
    }

    public void walkToWhere() {
        System.out.println("去哪里呢...");
    }

}
