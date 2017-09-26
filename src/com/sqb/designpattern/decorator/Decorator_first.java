package com.sqb.designpattern.decorator;

public class Decorator_first extends Decorator {

    public Decorator_first(Human human) {
        super(human);
    }
    
    public void goClothespress() {
        System.out.println("去衣柜找找看...");
    }
    
    public void findPlaceOnMap() {
        System.out.println("在Map上找找...");
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
