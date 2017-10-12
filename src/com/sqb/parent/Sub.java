package com.sqb.parent;

/**
 * 测试 子类继承父类，私有方法的问题
 * @author asus
 *
 */
public class Sub extends Par {
    @Override
    public void testPublic() {
        System.out.println("Sub Public");
    }
    
    private void testPrivate() {
        System.out.println("Sub Private");
    }
    
    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.test();
    }
}
