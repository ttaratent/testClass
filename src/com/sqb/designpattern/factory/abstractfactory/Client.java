package com.sqb.designpattern.factory.abstractfactory;

public class Client {
    public static void main(String[] args) {
        IFactory factory = new Factory();
        factory.createProduct1().show();
        factory.createProduct2().show();
    }
}
