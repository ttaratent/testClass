package com.sqb.structure.btree;

/**
 * 通过keyArray存储key值，通过bTreeArray存储与key值错位的链接节点
 * 自左向右查询 左小右大
 * @author asus
 */
public class BTreeNode {
    
    private Integer order;
    private Integer currentSize;
    private Integer[] keyArray; // 先使用Integer实现Key值
    private BTreeNode parent; // 父节点引用
    private BTreeNode[] bTreeChild; // 子节点引用
    
    public BTreeNode getParent() {
        return parent;
    }

    public void setParent(BTreeNode parent) {
        this.parent = parent;
    }

    public BTreeNode(Integer order, BTreeNode parent) {
        this.order = order;
        this.currentSize = 0;
        this.parent = parent;
        this.keyArray = new Integer[order+1];
        this.bTreeChild = new BTreeNode[order+2];
    }
    
    public BTreeNode(Integer order, BTreeNode parent, Integer currentSize, Integer[] keyArray, BTreeNode[] bTreeChild) {
        this.order = order;
        this.currentSize = currentSize;
        this.parent = parent;
        this.keyArray = keyArray;
        this.bTreeChild = bTreeChild;
    }
    
    // 查询在该节点的位置 实际B树每一个节点都应该存放Key和Value
//    public BTreeNode searchKeyIndex(Integer key) {
//        BTreeNode btree = null;
//        int index = 0;
//        for(index = 0; index < order; index++) {
//            Integer c1 = keyArray[index];
//            if(c1 > key) {
//                btree = bTreeArray[index-1];
//                return btree;
//            }
//        }
//        return bTreeArray[order];
//    }
    
    // 添加 如果添加完成返回NULL
    public void addNode(Integer key) {
        // 查询数据是否在该Node上
        for(int i = 0; i < currentSize; i++) {
            Integer tmp = keyArray[i];
            if(tmp > key) {
                BTreeNode child = bTreeChild[i];
                if(child != null) {
                    // 子节点进行添加
                    child.addNode(key);
                } else if(currentSize < order) {
                    moveKey(i);
                    keyArray[i] = key;
                    bTreeChild[i] = null;
                    currentSize++;
                } else {
                    // 进行拆分 TODO
                }
               return;
            }
        }
    }

    private void moveKey(int i) {
        // 移动keyArray
        for(int y = currentSize; y > i; y--) {
            keyArray[y] = keyArray[y-1];
        }
        keyArray[i] = null;
        // 移动bTreeChild
        for(int y = currentSize+1; y > i; y--) {
            bTreeChild[y] = bTreeChild[y-1];
        }
        bTreeChild[i] = null;
    }
    
    // 提供Key， 将原BTreeNode前部分保留，后部分转移到新Node中
    public void add(Integer key, BTreeNode child, BTreeNode child1) {
        for(int i = 0; i < currentSize + 1; i++) {
            BTreeNode tmp = bTreeChild[i];
            if(tmp == child) {
                if(currentSize < order) {
                    moveKey(i);
                    keyArray[i] = key;
                    bTreeChild[i] = child;
                    bTreeChild[i+1] = child1;
                    child.setParent(this);
                    child1.setParent(this);
                } else {
                    // 拆分 TODO
                    moveKey(i);
                    keyArray[i] = key;
                }
            }
        }
    }
}
