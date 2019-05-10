package com.sqb.structure.btree;

/**
 * ͨ��keyArray�洢keyֵ��ͨ��bTreeArray�洢��keyֵ��λ�����ӽڵ�
 * �������Ҳ�ѯ ��С�Ҵ�
 * @author asus
 */
public class BTreeNode {
    
    private Integer grade;
    private Integer[] keyArray; // ��ʹ��Integerʵ��Keyֵ
    private BTreeNode[] bTreeArray;
    
    public BTreeNode(Integer grade) {
        this.grade = grade;
        this.keyArray = new Integer[grade];
        this.bTreeArray = new BTreeNode[grade+1];
    }
    
    // ��ѯ�ڸýڵ��λ��
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
