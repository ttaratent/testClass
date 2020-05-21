package com.sqb.algorithm.learn;

public class LongestPalindromeSubString {
	
	public static void main(String[] args) {
		longestPalindrome("ccc");
	}
	
	public static String longestPalindrome(String s) {
		int i = 0;
		int len = s.length();
		String ret = "";
		for(; i < len; i++) {
			char c = s.charAt(i);
			String tmp = c +"";
			String tmp1 = c + "";
			// 判断是否是偶数回文
			if(i < len -1) {
				char r = s.charAt(i+1);
				if(c == r) {
					tmp = tmp + r;
					for(int x = i - 1, y = i + 2; x >= 0 && y < len; x--, y++) {
						char x1 = s.charAt(x);
						char x2 = s.charAt(y);
						if(x1 == x2) {
							tmp = x1 + tmp + x2;
						} else {
							break;
						}
					}
				}
			}
			
			// 奇数回文
			for(int x = i - 1, y = i + 1; x>= 0 && y < len; x--, y++) {
				char x1 = s.charAt(x);
				char x2 = s.charAt(y);
				if(x1 == x2) {
					tmp1 = x1 + tmp1 + x2;
				} else {
					break;
				}
			}
			
			if(ret.length() < tmp.length() && ret.length() < tmp1.length()) {
				if(tmp.length() > tmp1.length()) {
					ret = tmp;
				} else {
					ret = tmp1;
				}
			} else if(ret.length() < tmp.length()) {
				ret = tmp;
			} else if(ret.length() < tmp1.length()) {
				ret = tmp1;
			}
		}
		return ret;
    }
}
