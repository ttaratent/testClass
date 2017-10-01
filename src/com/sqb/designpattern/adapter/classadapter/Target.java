package com.sqb.designpattern.adapter.classadapter;

public interface Target {
    /**
     * 源类Adaptee有的方法
     */
    public void sampleOperation1();
    /**
     * 源类没有的方法
     */
    public void sampleOperation2();
}
