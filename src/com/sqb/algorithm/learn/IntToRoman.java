package com.sqb.algorithm.learn;

public class IntToRoman {
	
	public static void main(String[] args) {
		System.out.println(intToRoman(4));
	}
	
    public static String intToRoman(int num) {
    	int remainder = num;
    	String ret = "";
    	if(remainder >= 1000) {
    		int x = remainder / 1000;
    		remainder = remainder % 1000;
    		for(int i = 0; i < x; i++) {
    			ret = ret + "M";
    		}
    	}
    	if(remainder >= 500) {
    		int x = remainder / 500;
    		remainder = remainder % 500;
    		for(int i = 0; i < x; i++) {
    			ret = ret + "D";
    		}
    	}
    	if(remainder >= 100) {
    		int x = remainder / 100;
    		remainder = remainder % 100;
    		for(int i = 0; i < x; i++) {
    			ret = ret + "C";
    		}
    	}
    	if(remainder >= 50) {
    		int x = remainder / 50;
    		remainder = remainder % 50;
    		for(int i = 0; i < x; i++) {
    			ret = ret + "L";
    		}
    	}
    	if(remainder >= 10) {
    		int x = remainder / 10;
    		remainder = remainder % 10;
    		for(int i = 0; i < x; i++) {
    			ret = ret + "X";
    		}
    	}
    	if(remainder >= 5) {
    		int x = remainder / 5;
    		remainder = remainder % 5;
    		for(int i = 0; i < x; i++) {
    			ret = ret + "V";
    		}
    	}
    	
		int x = remainder;
		for(int i = 0; i < x; i++) {
			ret = ret + "I";
		}
		
		// 替换一些特殊情况
		// CM 900
		ret = ret.replaceAll("DCCCC", "CM");
		// CD 400 
		ret = ret.replaceAll("CCCC", "CD");
		// XC 90
		ret = ret.replaceAll("LXXXX", "XC");
		// XL 40
		ret = ret.replaceAll("XXXX", "XL");
		// IX 9
		ret = ret.replaceAll("VIIII", "IX");
		// IV 4
		ret = ret.replaceAll("IIII", "IV");
    	return ret;
    }
}
