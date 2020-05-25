package com.sqb.algorithm.learn;

public class RomanToInt {

	public int romanToInt(String s) {
		int len = s.length();
		int ret = 0;
        for(int i = 0; i < len; i++) {
        	char c = s.charAt(i);
        	if(c == 'M') {
        		ret += 1000;
        		continue;
        	}
        	if(c == 'D') {
        		ret += 500;
        		continue;
        	}
        	if(c == 'C') {
        		if(i < len -1) {
        			char n = s.charAt(i+1);
        			// 900
        			if(n == 'M') {
        				ret += 900;
        				i++;
        				continue;
        			}
        			
        			// 400
        			if(n == 'D') {
        				ret += 400;
        				i++;
        				continue;
        			}
        		}
        		ret += 100;
        		continue;
        	}
        	if(c == 'L') {
        		ret += 50;
        		continue;
        	}
        	if(c == 'X') {
        		if(i < len -1) {
        			char n = s.charAt(i+1);
        			// 90
        			if(n == 'C') {
        				ret += 90;
        				i++;
        				continue;
        			}
        			
        			// 40
        			if(n == 'L') {
        				ret += 40;
        				i++;
        				continue;
        			}
        		}
        		ret += 10;
        		continue;
        	}
        	
        	if(c == 'V') {
        		ret += 5;
        		continue;
        	}
        	if(c == 'I') {
        		if(i < len -1) {
        			char n = s.charAt(i+1);
        			// 9
        			if(n == 'X') {
        				ret += 9;
        				i++;
        				continue;
        			}
        			
        			// 4
        			if(n == 'V') {
        				ret += 4;
        				i++;
        				continue;
        			}
        		}
        		ret += 1;
        		continue;
        	}
        }
        return ret;
    }
}
