package com.sqb.algorithm.learn;

/**
 * һ���ַ��� s ��һ���ַ����� p��������ʵ��һ��֧�� '.' �� '*' ��������ʽƥ�䡣
 */
public class RegMatch {
	
	public static void main(String[] args) {
		System.out.println(isMatch("aab","c*a*b"));
	}
	
	// �ݹ� ˼· 
	// У������ĸ�Ƿ�ƥ�� 
	// ��������Ƿ�ƥ��
	// ����"" "a*c*" ��Ҫ��a* ��ƥ����д���
	// "a*a*a*b" ���ȶ��жϺ����Ƿ�ƥ�����  ��Ȼ���׳�ʱ
	public static boolean isMatch(String s, String p) {
		// ����Ƕ���ַ�*�Ŀ���ƥ����ַ�
		if(p.isEmpty()) {
			return s.isEmpty();
		}
		boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
		// ���ǺŽ��д���
		if(p.length() > 1 && p.charAt(1) == '*') {
			// ��ǰ�ַ�ƥ��     ��ǰ�ַ���ƥ��
			return isMatch(s, p.substring(2)) || (firstMatch && (isMatch(s.substring(1), p)));
		} else {
			return firstMatch && isMatch(s.substring(1), p.substring(1));
		}
	}
	
	// TODO δʵ��
	public static boolean isMatch1(String s, String p) {
		// �ַ�ƥ���־λ
		int i = 0;
		int j = 0;
		
		String[] special = new String[p.length()];
		int specialSize = 0;
		String[] nor = new String[p.length()];
		int norSize = 0;
		
		// Ԥ���� ƥ���ַ���
		String norCur = "";
		for(int n = 0; n < p.length(); n++) {
			char pCur = p.charAt(n);
			if(pCur == '.') {
				// �жϺ�һλ�Ƿ�Ϊ*
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
		
		// ����ƥ��
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
						// ����к��������ж���n2֮ǰ�Ƿ��ַ���Ϊo
						// TODO ���ԭ�ַ�����������Ӧ�ģ���һ��ƥ��ǰһ����ƥ��
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
						// û�к������жϺ����ַ����Ƿ�Ϊo
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
						// ����к��������ж���n2֮ǰ�Ƿ��ַ���Ϊo
						// TODO ���ԭ�ַ�����������Ӧ�ģ���һ��ƥ��ǰһ����ƥ��
						int n2Index = s.indexOf(n2, charIndex);
						if(n2Index < 0) {
							return false;
						} else {
							charIndex = n2Index;
						}
					} else {
						// û�к������жϺ����ַ����Ƿ�Ϊo
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
