package com.sqb.reflect;

public class SuperMan extends Person implements ActionInterface {

    private boolean BlueBriefs;
    
    public void fly()
    {
        System.out.println("超人会飞耶~~");
    }
    
    public boolean isBlueBriefs() {
        return BlueBriefs;
    }


    public void setBlueBriefs(boolean blueBriefs) {
        BlueBriefs = blueBriefs;
    }


    @Override
    public void walk(int m) {
        System.out.println("超人会飞耶~~走了" + m + "米就走不动了！");
    }

}
