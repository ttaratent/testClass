package com.sqb.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallable {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		Callable<String> call = new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(1000 * 10); //休眠指定的时间，此处表示该操作比较耗时
				return "Other less important but longtime things.";
			}
		};
		Future<String> task = exec.submit(call);
		//重要的事情
		System.out.println("Let's do important things. start");
		Thread.sleep(1000 * 3);
		System.out.println("Let's do important things. end");
		
		//不重要的事情
		while(! task.isDone()) {
			System.out.println("still waiting....");
			Thread.sleep(1000 * 1);
		}
		System.out.println("get sth....");
		String obj = task.get();
		System.out.println(obj);
		//关闭线程
		exec.shutdown();
	}

}
