package com.sqb.reflect;

public class SuperMan extends Person implements ActionInterface {

    private boolean BlueBriefs;
    
    public void fly()
    {
        System.out.println("���˻��Ү~~");
    }
    
    public boolean isBlueBriefs() {
        return BlueBriefs;
    }


    public void setBlueBriefs(boolean blueBriefs) {
        BlueBriefs = blueBriefs;
    }


    @Override
    public void walk(int m) {
        System.out.println("���˻��Ү~~����" + m + "�׾��߲����ˣ�");
    }

}
