package com.sqb.algorithm.learn;

/**
 * ���������
 */
public class LargestRectangleArea {
	
	public static void main(String[] args) {
		System.out.println(largestRectangleArea(new int[] {2,1,5,6,2,3}));
	}
	
	public static int largestRectangleArea(int[] heights) {
		int area = 0;
		for(int i = 0; i < heights.length; i++) {
			// һ��
			int h1 = heights[i];
			for(int j = i; j < heights.length; j++) {
				// ��2
				int h2 = heights[j];
				h1 = Math.min(h1,h2);
				area = Math.max(area, (j - i + 1) * h1);
			}
		}
		return area;
    }
}
