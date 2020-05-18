package com.sqb.algorithm.learn;

public class MaxProduct {
	
	public int maxProduct(int[] nums) {
        int a=1;  
        int max=nums[0];
        
        for(int num:nums){
            a=a*num;
            if(max<a)max=a;
            if(num==0)a=1;

        }
        a=1;
        for(int i=nums.length-1;i>=0;i--){
            a=a*nums[i];
            if(max<a)max=a;
            if(nums[i]==0)a=1;
        }  
        return max;
    }
}
