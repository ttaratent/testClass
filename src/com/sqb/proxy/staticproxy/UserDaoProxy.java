package com.sqb.proxy.staticproxy;

public class UserDaoProxy implements IUserDao {

    //���ܱ���Ŀ�����
    private IUserDao target;
    
    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("��ʼ����...");
        target.save();
        System.out.println("�ύ����...");
    }
    
}
