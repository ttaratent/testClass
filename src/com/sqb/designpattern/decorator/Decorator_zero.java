package com.sqb.designpattern.decorator;

// 下面定义三种装饰，这是第一个，第二个第三个功能依次细化，即装饰着的功能越来越多
public class Decorator_zero extends Decorator {

    public Decorator_zero(Human human) {
        super(human);
    }
    
    public void goHome() {
        System.out.println("进房子...");
    }

    public void findMap() {
        System.out.println("书房找找Map...");
    }
    
    public void wearClothes() {
        super.wearClothes();
        goHome();
    }

    public void walkToWhere() {
        super.walkToWhere();
        findMap();
    }

}
