package com.sqb.sundry;


//Java�и�ֵ˳��
//1�����ྲ̬������ֵ
//2������̬������ֵ
//3�������Ա������ֵ�͸���鸳ֵ
//4�����๹�캯����ֵ
//5�������Ա������ֵ������鸳ֵ
//6�������캯����ֵ
public class StaticTest {
	
	//1������׼���׶Σ�st=null��b=0
	//2����˳��̬���֣���ִ��new StaticTest()��ִ�д���飬����ֵa=110��ִ�й��캯��
	//3��֮��ֱ�ִ�о�̬�����ͷ���
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
