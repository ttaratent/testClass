package com.sqb.designpattern.singleton;

public class Singleton1Childb extends Singleton1 {
    
    public static Singleton1Childb getInstance() {
        return (Singleton1Childb) Singleton1Childb
                .getInstance("com.sqb.designpattern.singleton.Singleton1Childb");
    }
    
    public String about() {
        return "---->����Singleton1�ĵڶ�������Singleton1Childb";
    }
}
