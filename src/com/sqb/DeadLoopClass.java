package com.sqb;

public class DeadLoopClass {
	
	static {
		/*������������if��䣬����������ʾ��Initializer does not complete normally ���ܾ����롱*/
		if (true) {
			System.out.println(Thread.currentThread() + "init DeadLoopClass");
//			while (true) {
//			}
		}
	}
	
	public static void main(String[] args) {
		Runnable script = new Runnable() {
			public void run() {
				System.out.println(Thread.currentThread() + "start");
				DeadLoopClass dlc = new DeadLoopClass();
				System.out.println(Thread.currentThread() + "run over");
			}
		};
		
		Thread thread1 = new Thread(script);
		Thread thread2 = new Thread(script);
		thread1.start();
		thread2.start();
	}

}
