package com.sqb.sundry;

import java.util.StringTokenizer;

public class TestStringTokenizer {

	public static void main(String[] args) {
		String orgStr = "swe,wqeq,qwe,qwe,asd,zxc";
		StringTokenizer token = new StringTokenizer(orgStr,",");
		String[] result = new String[token.countTokens()];
		int i = 0;
		while(token.hasMoreTokens()){
			result[i++] = token.nextToken();
		}
		for (String string : result) {
			System.out.println(string);
		}
	}

}
