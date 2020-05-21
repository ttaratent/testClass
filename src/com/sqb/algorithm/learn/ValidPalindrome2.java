package com.sqb.algorithm.learn;

public class ValidPalindrome2 {
	
	public static void main(String[] args) {
//		eedede
		validPalindrome("ebcbbececabbacecbbcbe");
	}
	
	public static boolean validPalindrome(String s) {
		for(int i = 0, j = s.length() - 1; i < j; i++, j--) {
			char ix = s.charAt(i);
			char jx = s.charAt(j);
			if(ix != jx) {
				// 忽略一个不同
				if(i + 1 != j) {
					boolean x = true;
					for(int i1 = i +1, j1 = j; i1 < j1; i1++, j1--) {
						char ix1 = s.charAt(i1);
						char jx1 = s.charAt(j1);
						if(ix1 != jx1) {
							x = false;
							break;
						}
					}
					if(x) {
						return x;
					} else {
						for(int i1 = i, j1 = j-1; i1 < j1; i1++, j1--) {
							char ix1 = s.charAt(i1);
							char jx1 = s.charAt(j1);
							if(ix1 != jx1) {
								return false;
							}
						}
						return true;
					}
					
				}
			}
		}
		return true;
    }
	
}
