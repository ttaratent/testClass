package com.sqb.designpattern.observer;

public class MySubject extends AbstractSubject{

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObserver();
    }
    
}
