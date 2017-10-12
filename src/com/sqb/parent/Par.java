package com.sqb.parent;

public class Par {
    
    public void test() {
        System.out.println(this);
        this.testPublic();
        this.testPrivate();
        testPublic();
        testPrivate();
        
    }
    
    public void testPublic() {
        System.out.println("Par Public");
    }
    
    private void testPrivate() {
        System.out.println("Par Private");
    }
}
