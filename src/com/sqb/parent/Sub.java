package com.sqb.parent;

/**
 * ���� ����̳и��࣬˽�з���������
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
