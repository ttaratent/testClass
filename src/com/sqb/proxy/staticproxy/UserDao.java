package com.sqb.proxy.staticproxy;

public class UserDao implements IUserDao {

    @Override
    public void save() {
        System.out.println("----�Ѿ��������ݣ�----");
    }
}
