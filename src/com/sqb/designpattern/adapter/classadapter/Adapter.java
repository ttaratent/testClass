package com.sqb.designpattern.adapter.classadapter;

public class Adapter extends Adaptee implements Target {

    /**
     * 补充没有的方法 
     */
    public void sampleOperation2() {
        System.out.println("sampleOperation2");
    }

}
