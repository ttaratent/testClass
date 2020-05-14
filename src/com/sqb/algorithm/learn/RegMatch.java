package com.sqb.algorithm.learn;

/**
 * 一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 */
public class RegMatch {
	
	public static void main(String[] args) {
		System.out.println(isMatch("aab","c*a*b"));
	}
	
	// 递归 思路 
	// 校验首字母是否匹配 
	// 检验后续是否匹配
	// 存在"" "a*c*" 需要对a* 不匹配进行处理
	// "a*a*a*b" 优先对判断后续是否匹配得上  不然容易超时
	public static boolean isMatch(String s, String p) {
		// 如果是多个字符*的可以匹配空字符
		if(p.isEmpty()) {
			return s.isEmpty();
		}
		boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
		// 对星号进行处理
		if(p.length() > 1 && p.charAt(1) == '*') {
			// 当前字符匹配     当前字符不匹配
			return isMatch(s, p.substring(2)) || (firstMatch && (isMatch(s.substring(1), p)));
		} else {
			return firstMatch && isMatch(s.substring(1), p.substring(1));
		}
	}
	
	// TODO 未实现
	public static boolean isMatch1(String s, String p) {
		// 字符匹配标志位
		int i = 0;
		int j = 0;
		
		String[] special = new String[p.length()];
		int specialSize = 0;
		String[] nor = new String[p.length()];
		int norSize = 0;
		
		// 预处理 匹配字符串
		String norCur = "";
		for(int n = 0; n < p.length(); n++) {
			char pCur = p.charAt(n);
			if(pCur == '.') {
				// 判断后一位是否为*
				if(n+1 < p.length()) {
					char pNext = p.charAt(n+1);
					if(pNext == '*') {
						special[specialSize++] = ".*";
						n++;
					} else {
						special[specialSize++] = ".";
					}
				} else {
					special[specialSize++] = ".";
				}
				nor[norSize++] = norCur;
				norCur = "";
			} else if(pCur == '*') {
				special[specialSize++] = "*";
				nor[norSize++] = norCur;
				norCur = "";
			} else {
				norCur += pCur;
			}
		}
		if(!"".equals(norCur)) {
			nor[norSize++] = norCur;
			norCur = "";
		}
		
		// 进行匹配
		int specIndex = 0;
		int norIndex = 0;
		int charIndex = 0;
		while(true) {
			if(norIndex < norSize) {
				String n1 = nor[norIndex++];
				if(s.indexOf(n1, charIndex) != charIndex) {
					return false;
				}
				charIndex += n1.length();
			}
			if(specIndex < specialSize) {
				String s1 = special[specIndex++];
				if(".".equals(s1)) {
					if(charIndex + 1 >= s.length()) {
						return false;
					}
					charIndex++;
				} else if("*".equals(s1)) {
					char o = s1.charAt(charIndex - 1);
					if(norIndex + 1 < norSize) {
						String n2 = nor[norIndex+1];
						// 如果有后续，则判断在n2之前是否字符串为o
						// TODO 如果原字符串有两个对应的，后一个匹配前一个不匹配
						int n2Index = s.indexOf(n2, charIndex);
						if(n2Index < 0) {
							return false;
						} else {
							for(; charIndex < n2Index; charIndex++) {
								char tmp = s.charAt(charIndex);
								if(tmp != o) {
									return false;
								}
							}
						}
					} else {
						// 没有后续，判断后面字符串是否都为o
						for(; charIndex < s.length(); charIndex++) {
							char tmp = s.charAt(charIndex);
							if(tmp != o) {
								return false;
							}
						}
						return true;
					}
				} else {
					// .*
					if(norIndex + 1 < norSize) {
						String n2 = nor[norIndex+1];
						// 如果有后续，则判断在n2之前是否字符串为o
						// TODO 如果原字符串有两个对应的，后一个匹配前一个不匹配
						int n2Index = s.indexOf(n2, charIndex);
						if(n2Index < 0) {
							return false;
						} else {
							charIndex = n2Index;
						}
					} else {
						// 没有后续，判断后面字符串是否都为o
						return true;
					}
				}
			}
			if(charIndex >= s.length() || (norIndex >= norSize && specIndex >= specialSize)) {
				return true;
			}
		}
		
    }
	
}
