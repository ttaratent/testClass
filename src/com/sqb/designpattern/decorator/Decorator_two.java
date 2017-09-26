package com.sqb.designpattern.decorator;

public class Decorator_two extends Decorator {

    public Decorator_two(Human human) {
        super(human);
    }
    
    public void findClothes() {
        System.out.println("�ҵ�һ��D&G...");
    }
    
    public void findTheTarget() {
        System.out.println("��Map���ҵ����ػ�԰�ͳǱ�...");
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
