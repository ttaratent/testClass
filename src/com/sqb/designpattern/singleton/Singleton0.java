package com.sqb.designpattern.singleton;

// ����ʽ
public class Singleton0 {
    
    // ������̬������ֱ�Ӵ���ʵ��
    private static Singleton0 singleton0 = new Singleton0();
    
    private Singleton0() {
        // ˽�й��캯��
    }
    
    // ����һ�����з���
    public static Singleton0 getInstance() {
        return singleton0;
    }
}
