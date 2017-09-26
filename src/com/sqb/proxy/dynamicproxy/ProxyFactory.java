package com.sqb.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    // ά��һ��Ŀ�����
    private Object target;
    public ProxyFactory(Object target) {
        this.target = target;
    }
    
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
                target.getClass().getInterfaces(), 
                new InvocationHandler() {
                    
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("��ʼ����2");
                        // ִ��Ŀ����󷽷�
                        Object returnValue = method.invoke(target, args);
                        System.out.println("�ύ����2");
                        return returnValue;
                    }
                });
    }
}