package com.sqb.algorithm.learn;

/**
 * ����һ���ǿ��������飬����ĳ��Ԫ��ֻ����һ�����⣬����ÿ��Ԫ�ؾ��������Ρ��ҳ��Ǹ�ֻ������һ�ε�Ԫ�ء�
 */
public class SingleNumber {
	
	public int singleNumber(int[] nums) {
		int r = 0;
        for(int i = 0; i < nums.length; i++) {
        	int n = nums[i];
        	r ^= n;
        }
        return r;
    }
}
