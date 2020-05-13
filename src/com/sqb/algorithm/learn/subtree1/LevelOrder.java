package com.sqb.algorithm.learn.subtree1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树层次遍历
 */
public class LevelOrder {
	// BFS宽度优先算法 
	// DFS深度优先算法

	// 双队列
	public List<List<Integer>> levelOrder1(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if(root == null) {
			return result;
		}
		List<TreeNode> queueList = new LinkedList<TreeNode>();
		queueList.add(root);
		while(!queueList.isEmpty()) {
			int size = queueList.size();
			List<Integer> list = new ArrayList<>();
			for(int i = 0; i < size; i++) {
				TreeNode t = queueList.remove(0);
				list.add(t.val);
				if(t.left != null) {
					queueList.add(t.left);
				}
				if(t.right != null) {
					queueList.add(t.right);
				}
			}
			result.add(list);
		}
		return result;
	}
	
	// 递归
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> r = new ArrayList<>();
		if(root == null) {
			return result;
		}
		r.add(root.val);
		result.add(r);
		List<TreeNode> p = new ArrayList<>();
		p.add(root);
		while(true) {
			List<TreeNode> clist = levelOrderNode(p);
			if(clist.size() == 0) {
				break;
			}
			List<Integer> c = new ArrayList<>();
			for(int i = 0; i < clist.size(); i++) {
				TreeNode n = clist.get(i);
				c.add(n.val);
			}
			result.add(c);
			p = clist;
		}
		return result;
    }
	
	public List<TreeNode> levelOrderNode(List<TreeNode> plist) {
		List<TreeNode> clist = new ArrayList<>();
		for(int i = 0; i < plist.size(); i++) {
			TreeNode p = plist.get(i);
			if(p.left != null) {
				clist.add(p.left);
			}
			if(p.right != null) {
				clist.add(p.right);
			}
		}
		return clist;
	}
}
