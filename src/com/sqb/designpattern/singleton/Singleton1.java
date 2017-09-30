package com.sqb.designpattern.singleton;

import java.util.HashMap;
import java.util.Map;

// 登记式 父类
// 采用Map配置多个单例
public class Singleton1 {
    
    // 设立静态变量，直接创建实例
    private static Map<String, Singleton1> map = new HashMap<String, Singleton1>();
    
    // ----受保护的-----构造函数，不能是私有的，但是这样子类可以直接访问构造方法了
    // 解决方式是把你的单例类放到一个外在的包中，以便在其他包中的类（包括缺省的包）无法实例化一个单例类
    protected Singleton1() {
        System.out.println("-->私有化构造函数被调用，创建实例中");
    }
    
    public static Singleton1 getInstance(String name) {
        if (name == null) {
            name = Singleton1.class.getName();
            System.out.println("-->name不存在，那么赋值等于" + Singleton1.class.getName());
        }
        if (map.get(name) == null) {
            try {
                System.out.println("-->name对应的值不存在，开始创建");
                map.put(name, (Singleton1) Class.forName(name).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("-->name对应的值存在");
        }
        System.out.println("-->返回name对应的值");
        return map.get(name);
    }
    
    public Map<String, Singleton1> getMap() {
        return map;
    }
}
