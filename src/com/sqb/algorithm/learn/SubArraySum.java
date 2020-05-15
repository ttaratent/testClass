package com.sqb.algorithm.learn;

import java.util.HashMap;

/**
 * 求数组中连续子数组和为k出现的个数
 */
public class SubArraySum {
	
	public static void main(String[] args) {
		subarraySum(new int[] {-1,-1,1}, 0);
	}
	
	// 枚举
	public static int subarraySum1(int[] nums, int k) {
		int n = 0;
		for(int i = 0; i < nums.length; i++) {
			int x = nums[i];
			int sum = 0;
			for(int j = i; j >= 0; j--) {
				sum+=nums[j];
				if(sum == k) {
					n++;
				}
			}
		}
		return n;
    }
	
	// => sum(i) - sum(j) = k => sum(j) = sum(i) - k存在即可
	public static int subarraySum(int[] nums, int k) {
		// 前i-1位的和
		int pre = 0; 
		// 共出现n次
		int n = 0;
		HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>();
		mp.put(0, 1);
		for(int i = 0; i < nums.length; i++) {
			pre += nums[i];
			if(mp.containsKey(pre - k)) {
				n += mp.get(pre - k);
			}
			mp.put(pre, mp.getOrDefault(pre, 0) + 1);
		}
		return n;
	}
}
