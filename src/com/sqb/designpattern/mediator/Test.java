package com.sqb.designpattern.mediator;

public class Test {
    public static void main(String[] args) {
        
        Mediator mediator = new MyMediator();
        
        mediator.createMediator();
        
        mediator.workAll();
    }
}
