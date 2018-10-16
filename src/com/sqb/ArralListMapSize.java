package com.sqb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArralListMapSize {
    
    // 通过控制台查看其大致占用120M内存,
    // 通过 jmap -dump:format=b,file=d:\dump.txt <pid> 导出一个二进制dump文件
    // 通过 jhat filename; 将dump文件转成一个7000端口的网页用于查看堆内存信息
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
