package com.sqb.array;

public class Test {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
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
