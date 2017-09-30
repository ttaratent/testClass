package com.sqb.designpattern.singleton;

import java.util.HashMap;
import java.util.Map;

// �Ǽ�ʽ ����
// ����Map���ö������
public class Singleton1 {
    
    // ������̬������ֱ�Ӵ���ʵ��
    private static Map<String, Singleton1> map = new HashMap<String, Singleton1>();
    
    // ----�ܱ�����-----���캯����������˽�еģ����������������ֱ�ӷ��ʹ��췽����
    // �����ʽ�ǰ���ĵ�����ŵ�һ�����ڵİ��У��Ա����������е��ࣨ����ȱʡ�İ����޷�ʵ����һ��������
    protected Singleton1() {
        System.out.println("-->˽�л����캯�������ã�����ʵ����");
    }
    
    public static Singleton1 getInstance(String name) {
        if (name == null) {
            name = Singleton1.class.getName();
            System.out.println("-->name�����ڣ���ô��ֵ����" + Singleton1.class.getName());
        }
        if (map.get(name) == null) {
            try {
                System.out.println("-->name��Ӧ��ֵ�����ڣ���ʼ����");
                map.put(name, (Singleton1) Class.forName(name).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("-->name��Ӧ��ֵ����");
        }
        System.out.println("-->����name��Ӧ��ֵ");
        return map.get(name);
    }
    
    public Map<String, Singleton1> getMap() {
        return map;
    }
}
