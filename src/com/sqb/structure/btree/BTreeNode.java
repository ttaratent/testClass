package com.sqb.structure.btree;

/**
 * ͨ��keyArray�洢keyֵ��ͨ��bTreeArray�洢��keyֵ��λ�����ӽڵ�
 * �������Ҳ�ѯ ��С�Ҵ�
 * @author asus
 */
public class BTreeNode {
    
    private Integer order;
    private Integer currentSize;
    private Integer[] keyArray; // ��ʹ��Integerʵ��Keyֵ
    private BTreeNode parent; // ���ڵ�����
    private BTreeNode[] bTreeChild; // �ӽڵ�����
    
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
    
    // ��ѯ�ڸýڵ��λ�� ʵ��B��ÿһ���ڵ㶼Ӧ�ô��Key��Value
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
    
    // ��� ��������ɷ���NULL
    public void addNode(Integer key) {
        // ��ѯ�����Ƿ��ڸ�Node��
        for(int i = 0; i < currentSize; i++) {
            Integer tmp = keyArray[i];
            if(tmp > key) {
                BTreeNode child = bTreeChild[i];
                if(child != null) {
                    // �ӽڵ�������
                    child.addNode(key);
                } else if(currentSize < order) {
                    moveKey(i);
                    keyArray[i] = key;
                    bTreeChild[i] = null;
                    currentSize++;
                } else {
                    // ���в�� TODO
                }
               return;
            }
        }
    }

    private void moveKey(int i) {
        // �ƶ�keyArray
        for(int y = currentSize; y > i; y--) {
            keyArray[y] = keyArray[y-1];
        }
        keyArray[i] = null;
        // �ƶ�bTreeChild
        for(int y = currentSize+1; y > i; y--) {
            bTreeChild[y] = bTreeChild[y-1];
        }
        bTreeChild[i] = null;
    }
    
    // �ṩKey�� ��ԭBTreeNodeǰ���ֱ������󲿷�ת�Ƶ���Node��
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
                    // ��� TODO
                    moveKey(i);
                    keyArray[i] = key;
                }
            }
        }
    }
}
