package com.sqb.designpattern.singleton;

public class Singleton1Childa extends Singleton1 {
    
    public static Singleton1Childa getInstance() {
        return (Singleton1Childa) Singleton1Childa.getInstance("com.sqb.designpattern.singleton.Singleton1Childa");
    }
    
    public String about() {
        return "---->����Singleton1�ĵ�һ������Singleton1Childa";
    }
}
