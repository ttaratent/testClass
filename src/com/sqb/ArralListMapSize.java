package com.sqb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArralListMapSize {
    
    // ͨ������̨�鿴�����ռ��120M�ڴ�,
    // ͨ�� jmap -dump:format=b,file=d:\dump.txt <pid> ����һ��������dump�ļ�
    // ͨ�� jhat filename; ��dump�ļ�ת��һ��7000�˿ڵ���ҳ���ڲ鿴���ڴ���Ϣ
    public static void main(String[] args) {
        List<Map<Integer, Integer>> list = new ArrayList<Map<Integer, Integer>>();
        
        for(int i = 0; i < 2000; i++) {
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int j = 0; j < 20; j++) {
                map.put(j, j);
            }
            list.add(map);
        }
        
        System.out.println("1111");
    }
}
