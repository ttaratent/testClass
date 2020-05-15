package com.sqb.algorithm.learn;

/**
 * һ�����У����������±�һ�𹹳ɵ�����ı������
 */
public class NumSeriesMaxArea {
	
	// 1. ����ѭ�� ���� ��ѯ������
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
	
	// ͷβ˫ָ�� 
	public int maxArea1(int[] height) {
		int i = 0;
		int j = height.length - 1;
		int max = 0;
		for(;i >= j;) {
			
		}
	}
}
