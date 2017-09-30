package com.sqb.designpattern.singleton;

// 饿汉式
public class Singleton0 {
    
    // 设立静态变量，直接创建实例
    private static Singleton0 singleton0 = new Singleton0();
    
    private Singleton0() {
        // 私有构造函数
    }
    
    // 开放一个公有方法
    public static Singleton0 getInstance() {
        return singleton0;
    }
}
