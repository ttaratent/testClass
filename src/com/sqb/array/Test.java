package com.sqb.array;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test4() {
        Person person_01 = new Person("chenssy_01");

        Person[] persons1 = new Person[] { person_01 };
        Person[] persons2 = Arrays.copyOf(persons1, persons1.length);

        System.out.println("数组persons1:");
        display(persons1);
        System.out.println("---------------------");
        System.out.println("数组persons2:");
        display(persons2);
        // 改变其值
        persons2[0].setName("chessy_02");
        System.out.println("------------改变其值后------------");
        System.out.println("数组persons1:");
        display(persons1);
        System.out.println("---------------------");
        System.out.println("数组persons2:");
        display(persons2);
    }
    
    public static void display(Person[] persons){
        for(Person person : persons){
            System.out.println(person.toString());
        }
    }

    private static void test3() {
        int[] array = new int[10];
        Class clazz = array.getClass();
        System.out.println(clazz.getDeclaredFields().length);
        System.out.println(clazz.getDeclaredMethods().length);
        System.out.println(clazz.getDeclaredConstructors().length);
        System.out.println(clazz.getDeclaredAnnotations().length);
        System.out.println(clazz.getDeclaredClasses().length);
    }

    private static void test2() {
        int[] array_00 = new int[10];
        System.out.println("一维数组：" + array_00.getClass().getName());
        int[][] array_01 = new int[10][10];
        System.out.println("一维数组：" + array_01.getClass().getName());
        int[][][] array_02 = new int[10][10][10];
        System.out.println("一维数组：" + array_02.getClass().getName());
    }

    private static void test1() {
        int[] array = new int[10];
        System.out.println("array的父类是：" + array.getClass().getSuperclass());
        System.out.println("array的类名是：" + array.getClass());
    }
}
