package com.sqb.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Test {
    public void display(int i) throws MyException {
        if(i == 0) {
            throw new MyException("该值不能为0.....");
        } else {
            System.out.println( i / 2);
        }
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
        Test t = new Test();
        try {
            t.g();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        Test test = new Test();
        try {
            test.display(0);
            System.out.println("--------------------");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
    
    public void f() throws MyException{
        try {
            FileReader reader = new FileReader("G:\\myfile\\struts.txt");
            Scanner in = new Scanner(reader);
            System.out.println(in.next());
        } catch (FileNotFoundException e) {
            // e 保存异常信息
            throw new MyException("文件没有找到--01", e);
        }
    }
    
    public void g() throws MyException{
        try {
            f();
        } catch (MyException e) {
            // e 保存异常信息
            throw new MyException("文件没有找到--02", e);
        }
    }
}
