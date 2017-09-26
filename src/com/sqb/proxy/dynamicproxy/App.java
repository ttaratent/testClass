package com.sqb.proxy.dynamicproxy;

import com.sqb.proxy.staticproxy.IUserDao;
import com.sqb.proxy.staticproxy.UserDao;

public class App {
    public static void main(String[] args) {
        // Ŀ�����
        IUserDao target = new UserDao();
        // ��ԭʼ������ class��
        System.out.println(target.getClass());
        
        // ��Ŀ����󣬴����������
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        // class $Proxy0 �ڴ��ж�̬���ɵĴ������
        System.out.println(proxy.getClass());
        
        // ִ�з���   ���������
        proxy.save();
    }
}
