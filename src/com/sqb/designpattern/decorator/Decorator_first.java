package com.sqb.designpattern.decorator;

public class Decorator_first extends Decorator {

    public Decorator_first(Human human) {
        super(human);
    }
    
    public void goClothespress() {
        System.out.println("ȥ�¹����ҿ�...");
    }
    
    public void findPlaceOnMap() {
        System.out.println("��Map������...");
    }

    public void wearClothes() {
        super.wearClothes();
        findPlaceOnMap();
    }

    public void walkToWhere() {
        super.walkToWhere();
        findPlaceOnMap();
    }

}
