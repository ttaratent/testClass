package com.sqb.designpattern.decorator;

public class Decorator_two extends Decorator {

    public Decorator_two(Human human) {
        super(human);
    }
    
    public void findClothes() {
        System.out.println("找到一件D&G...");
    }
    
    public void findTheTarget() {
        System.out.println("在Map上找到神秘花园和城堡...");
    }

    public void wearClothes() {
        super.wearClothes();
        findClothes();
    }

    public void walkToWhere() {
        super.walkToWhere();
        findTheTarget();
    }

    
}
