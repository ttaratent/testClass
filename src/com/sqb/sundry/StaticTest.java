package com.sqb.sundry;


//Java中赋值顺序
//1、父类静态变量赋值
//2、自身静态变量赋值
//3、父类成员变量赋值和父类块赋值
//4、父类构造函数赋值
//5、自身成员变量赋值和自身块赋值
//6、自身构造函数赋值
public class StaticTest {
	
	//1、进入准备阶段，st=null，b=0
	//2、按顺序静态部分，先执行new StaticTest()，执行代码块，并赋值a=110，执行构造函数
	//3、之后分别执行静态代码块和方法
	public static void main(String[] args) {
		staticFunction();
	}
	
	static StaticTest st = new StaticTest();
	
	static
	{
		System.out.println("1");
	}
	
	{
		System.out.println("2");
	}
	
	StaticTest() {
		System.out.println("3");
		System.out.println("a=" + a + ",b=" + b);
	}

	private static void staticFunction() {
		System.out.println("4");
	}
	
	int a=110;
	static int b=112;
	
}
