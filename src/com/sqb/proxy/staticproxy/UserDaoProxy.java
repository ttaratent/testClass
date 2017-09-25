package com.sqb.proxy.staticproxy;

public class UserDaoProxy implements IUserDao {

    //接受保存目标对象
    private IUserDao target;
    
    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开始事务...");
        target.save();
        System.out.println("提交事务...");
    }
    
}
