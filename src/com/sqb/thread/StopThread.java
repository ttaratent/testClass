package com.sqb.thread;

import java.util.concurrent.TimeUnit;

public class StopThread {

//    private static volatile boolean stopRequested; // 通过添加关键字volatile将变量暴露出来
    private static boolean stopRequested;

    public static void main(String[] args) throws InterruptedException {

//        Thread backgroundThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while (!stopRequested) {
//                    i++;
//                }
//            }
//        });
//
//        backgroundThread.start();
//        TimeUnit.SECONDS.sleep(1);
//        stopRequested = true;
        test();
    }
    
    
    private static void test() throws InterruptedException {

        Thread backgroundThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopRequested) {
                    i++;
                    System.out.println(""+i);
                }
            }
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
    
    
}