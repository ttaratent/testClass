package com.sqb.algorithm.learn;

/**
 * 能在常数时间内检索到最小元素的栈
 */
public class MinStack {
	Node stack = null;
	
	public MinStack() {
		
	}
	
	public void push(int x) {
		if(stack == null) {
			stack = new Node();
			stack.val = x;
			stack.min = x;
			stack.next = null;
		} else {
			Node n = new Node();
			n.val = x;
			if(stack.min < x) {
				n.min = stack.min;
			} else {
				n.min = x;
			}
			n.next = stack;
			stack = n;
		}
    }
    
    public void pop() {
    	if(stack != null) {
    		Node p = stack.next;
    		stack = p;
    	}
    }
    
    public int top() {
    	if(stack != null) {
    		return stack.val;
    	} else {
    		return 0;
    	}
    }
    
    public int getMin() {
    	if(stack != null) {
    		return stack.min;
    	} else {
    		return 0;
    	}
    }
}


class Node {
	int val;
	int min; // 节点前的最小值
	Node next;
}
