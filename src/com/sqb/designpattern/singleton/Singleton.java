package com.sqb.designpattern.singleton;

// ����ʽ����ģʽ
public class Singleton {
    
    // ����˽�о�̬ʵ������ֹ�����ã��˴���ֵΪnull��Ŀ����ʵ���ӳټ���
    private static Singleton instance = null;
    
    // ˽�й��췽������ֹ��ʵ����
    private Singleton() {
        
    }
    
    // �˴�ʹ��һ���ڲ�����ά������
    private static class SingletonFactory {
        private static Singleton instance = new Singleton();
    }
    
    // ��̬���̷���������ʵ��
    public static Singleton getInstance() {
        
        return SingletonFactory.instance;
    }
    
    // ����ö����������л������Ա�֤���������л�ǰ�󱣳�һ��
    public Object readResolve(){
        return getInstance();
    }
}
