package com.sqb.algorithm.learn;

/**
 * 幂函数
 */
public class MyPow {
	
	public static void main(String[] args) {
		double myPow = myPow(2.00000, -5);
		System.out.println(myPow);
	}
	
	/**
	 * 快速幂算法
	 * @param x
	 * @param n
	 * @return
	 */
	public static double myPow(double x, int n) {
		double ans = 1;
		boolean positive = true;
		double ctx = x;
		int i = 0;
		if(n < 0) {
			positive = false;
		}
		while(n != 0 && i < 31) {
			if(positive) {
				if((n & 1) != 0) {
					ans *= ctx;
				}
			} else {
				if((n & 1) != 1) {
					ans *= ctx;
				}
			}
			ctx *= ctx;
			n >>= 1;
			i++;
		}
		if(!positive) {
			ans = ans * x;
			ans = 1 / ans;
		}
		return ans;
	}
}
