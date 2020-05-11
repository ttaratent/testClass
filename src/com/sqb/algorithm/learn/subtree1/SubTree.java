package com.sqb.algorithm.learn.subtree1;

/**
 * 
 */
public class SubTree {
	public static void main(String[] args) {
		TreeNode a = new TreeNode();
		a.val = 4;
		a.left = new TreeNode(1);
		a.right = new TreeNode(2);
		TreeNode b = new TreeNode();
		b.val = 4;
		b.left = new TreeNode(1);
		b.right = new TreeNode(2);
		System.out.println(isIdentical(a, b));
	}
	
	// 非空树
	public static boolean isSubtree(TreeNode s, TreeNode t) {
		// 比较本身是否相同
		if(isIdentical(s, t)) {
			return true;
		}
		TreeNode left = s.left;
		if(left != null) {
			if(isIdentical(left, t)) {
				return true;
			} else {
				if(isSubtree(left, t)) {
					return true;
				}
			}
		}
		TreeNode right = s.right;
		if(right != null) {
			if(isIdentical(right, t)) {
				return true;
			} else {
				if(isSubtree(right, t)) {
					return true;
				}
			}
		}
		
        return false;
    }
	
	private static boolean isIdentical(TreeNode t1, TreeNode t2) {
		if(t1 == null && t2 == null) {
			return true;
		}
		if(isSingleNull(t1, t2) || isSingleNull(t2, t1)) {
			return false;
		}
		// 比较值
		int val1 = t1.val;
		int val2 = t2.val;
		if(val1 != val2) {
			return false;
		}
		// 比较左树
		TreeNode left1 = t1.left;
		TreeNode left2 = t2.left;
		if(isSingleNull(left1, left2) || isSingleNull(left2, left1)) {
			return false;
		}
		if(!isIdentical(left1, left2)) {
			return false;
		}
		// 比较右树
		TreeNode right1 = t1.right;
		TreeNode right2 = t2.right;
		if(isSingleNull(right2, right1) || isSingleNull(right1, right2)) {
			return false;
		}
		if(!isIdentical(right1, right2)) {
			return false;
		}
		return true;
		
	}
	
	public static boolean isSingleNull(TreeNode t1, TreeNode t2) {
		return t1 == null && t2 != null;
	}
}
