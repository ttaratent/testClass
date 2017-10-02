package com.sqb;

public class TestTryFinallly {

	public static void main(String[] args) {
//		System.out.println(TestTryFinallly.test());
//		TestTryFinallly t = new TestTryFinallly();
//		System.out.println(t.get());
		System.out.println(new TestTryFinallly().test1());
	}


	static int test() {
		int i = 0;
		try {
			return i;
		} finally {
			++i;
		}
	}
	
	public int get() {
		try {
			return 1;
		} finally {
			return 2;
		}
	}
	
	public int test1() {
		try {
			return func1();
		}finally {
			return func2();
		}
	}
	
	int func1() {
		System.out.println("func1");
		return 1;
	}
	
	int func2() {
		System.out.println("func2");
		return 2;
	}

}
