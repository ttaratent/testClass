package com.sqb.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ExceptionTest {
    public static void main(String[] args) {
//        test1();
        int[] a = {1,2,3,4};
        System.out.println(a[4]);
        System.out.println("我却执行了吗？？？");
    }

    private static void test1() {
        String file = "D:\\exceptionTest.txt";
        FileReader reader;
        try {
            reader = new FileReader(file);
            Scanner in = new Scanner(reader);
            String string = in.next();
            System.out.println(string + "不知道我有幸能够执行到不。。。。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("对不起，你执行不到。。。");
        } finally {
            System.out.println("finally 在执行...");
        }
    }
    
}
