package com.sqb.algorithm.learn;

import java.util.Stack;

public class ReverseKGroup {
	
	public static void main(String[] args) {
//		reverseKGroup(create(new int[] {1,2,3,4,5}), 3);
		reverseKGroup(create(new int[] {1,2}), 2);
	}
	
	public static ListNode create(int[] a) {
		ListNode b = new ListNode(a[0]);
		ListNode c = b;
		int len = a.length;
		for(int i = 1; i < len; i++) {
			int t = a[i];
			c.next = new ListNode(t);
			c = c.next;
		}
		return b;
	}
	
	public static ListNode reverseKGroup(ListNode head, int k) {
		Stack<ListNode> stack = new Stack(); 
		int i = 0;
		ListNode cur = head;
		ListNode result = null;
		ListNode resultCur = null;
		while(cur != null) {
			ListNode next = cur.next;
			cur.next = null;
			if(i >= k) {
				while(!stack.empty()) {
					ListNode tmp = stack.pop();
					if(resultCur != null) {
						resultCur.next = tmp;
						resultCur = resultCur.next;
					} else {
						result = tmp;
						resultCur = result;
					}
				}
				i = 0;
			}
			i++;
			stack.push(cur);				
			cur = next;
		}
		// 对非整除情况处理
		if(stack.size() > 0) {
			if(stack.size() == k) {
				while(!stack.empty()) {
					ListNode tmp = stack.pop();
					if(resultCur != null) {
						resultCur.next = tmp;
						resultCur = resultCur.next;
					} else {
						result = tmp;
						resultCur = result;
					}
				}
			} else {
				ListNode a = stack.pop();
				while(!stack.empty()) {
					ListNode t = stack.pop();
					t.next = a;
					a = t;
				}
				if(resultCur == null) {
					resultCur = a;
					result = resultCur;
				} else {
					resultCur.next = a;
				}
			}
		}
		return result;
    }
	
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
