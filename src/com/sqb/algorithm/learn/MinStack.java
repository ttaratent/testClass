package com.sqb.algorithm.learn;

/**
 * ���ڳ���ʱ���ڼ�������СԪ�ص�ջ
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
	int min; // �ڵ�ǰ����Сֵ
	Node next;
}
