package com.sqb.designpattern.adapter.objectadapter;

public class Adapter implements Target{
    
    private Adaptee adaptee;
    
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    
    public void sampleOperation1() {
        this.adaptee.sampleOperation1();
    }
    
    public void sampleOperation2() {
        System.out.println("sampleOperation2");
    }
}
