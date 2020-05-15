package com.sqb.algorithm.learn;

/**
 * 一个数列，求数列与下标一起构成的最大四边形面积
 */
public class NumSeriesMaxArea {
	
	// 1. 两个循环 遍历 查询最大面积
	public int maxArea(int[] height) {
		int len = height.length;
		int max = 0;
		for(int i = 0; i < len; i++) {
			int li = height[i];
			for(int j = i+1; j < len; j++) {
				int lj = height[j];
				int h = j - i;
				int min = Math.min(li, lj);
				int area = min * h;
				if(max < area) {
					max = area;
				}
			}
		}
		return max;
    }
	
	// 头尾双指针 
	public int maxArea1(int[] height) {
		int i = 0;
		int j = height.length - 1;
		int max = 0;
		for(;i >= j;) {
			
		}
	}
}
