package com.sqb.structure.btree;

/**
 * 通过keyArray存储key值，通过bTreeArray存储与key值错位的链接节点
 * 自左向右查询 左小右大
 * @author asus
 */
public class BTreeNode {
    
    private Integer grade;
    private Integer[] keyArray; // 先使用Integer实现Key值
    private BTreeNode[] bTreeArray;
    
    public BTreeNode(Integer grade) {
        this.grade = grade;
        this.keyArray = new Integer[grade];
        this.bTreeArray = new BTreeNode[grade+1];
    }
    
    // 查询在该节点的位置
    public BTreeNode searchKeyIndex(Integer key) {
        BTreeNode btree = null;
        int index = 0;
        for(index = 0; index < grade; index++) {
            Integer c1 = keyArray[index];
            if(c1 > key) {
                btree = bTreeArray[index-1];
                return btree;
            }
        }
        return bTreeArray[grade];
    }
}
