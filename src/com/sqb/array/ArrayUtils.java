package com.sqb.array;

import java.util.Arrays;

public class ArrayUtils {

    /**
     * �������������
     * @param datas ԭʼ����
     * @param newLen ���ݴ�С
     * @return
     */
    public static <T> T[] expandCapacity(T[] datas, int newLen) { 
        newLen = newLen < 0 ? datas.length : datas.length + newLen;
        // ����һ���µ�����
        return Arrays.copyOf(datas, newLen);
    }
    
    /**
     * ������������ݴ��� 1.5 ��
     * @param datas
     * @return
     */
    public static <T> T[] expandCapacity(T[] datas) {
        int newLen = (datas.length * 3) / 2;      // ����ԭʼ�����1.5��
        // ����һ���µ�����
        return Arrays.copyOf(datas, newLen);
    }
    
    /**
     * ������������ݲ���
     * @param datas
     * @param mulitiple
     * @return
     */
    public static <T> T[] expandCapacityMul(T[] datas, int mulitiple) {
        mulitiple = mulitiple < 0 ? 1 : mulitiple;
        int newLen = datas.length * mulitiple;
        return Arrays.copyOf(datas, newLen);
    }
    
    
}
