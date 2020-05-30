package com.sqb.algorithm.learn;

import java.util.Stack;

/**
 * 3[a]ÀàËÆµÄ×Ö·û Îªaaa
 */
public class DecodeString1 {
	
	public static void main(String[] args) {
		System.out.println(decodeString("3[a]2[bc]"));
		
	}

	public static String decodeString(String s) {
		String ret = "";
		Stack<String> stack = new Stack<>();
		for(int i = 0; i < s.length(); i++) {
			String t = s.charAt(i) + "";
			if("]".equals(t)) {
				String t1 = stack.pop();
				String re1 = "";
				String re = "";
				while(!"[".equals(t1)) {
					re = t1 + re;
					if(stack.size() > 0) {
						t1 = stack.pop();
					} else {
						break;
					}
				}
				if(stack.size() > 0) {
					String num = "";
					while(true) {
						if(stack.size()>0) {
							String nx = stack.peek();
							if(nx.matches("\\d")) {
								nx = stack.pop();
								num = nx + num;
							} else {
								break;
							}
						} else {
							break;
						}
					}
					int n = Integer.parseInt(num+"");
					for(int j = 0; j < n; j++) {
						re1 += re;
					}
				}
				stack.push(re1);
			} else {
				stack.push(t);
			}
		}
		while(stack.size()>0) {
			String t = stack.pop();
			ret = t + ret;
		}
		return ret;
    }
	
}
