package com.sqb.algorithm.learn;

/**
 * 回文数
 * @author
 *
 */
public class Palindrome {
	public boolean isPalindrome(int x) {
		// 负数必然不是回文数
		if(x < 0) {
			return false;
		}
		String xStr = x + "";
		String xResverseStr = "";
		for(int i = xStr.length() - 1; i >= 0 ; i--) {
			xResverseStr += xStr.charAt(i); 
		}
		int xResverseInt = 0;
		try {
			xResverseInt = Integer.valueOf(xResverseStr);
		} catch (NumberFormatException e) {
			return false;
		}
		if(x == xResverseInt) {
			return true;
		} else {
			return false;
		}
    }
}
