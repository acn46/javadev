package edu.drexel.domain;

public class ThreadExample {

	public static void main(String[] args) {
		System.out.println(Thread.currentThread() .getName()+ " - begin.");
		
//		sequential();
//		threading1();
//		threading2();
		threading3();
		
		try {
			// simulating heavy processing activities.
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread() .getName()+ " - end.");
	}

	private static void sequential() {
		System.out.println(Thread.currentThread() .getName()+ " - task A");
		System.out.println(Thread.currentThread() .getName()+ " - task B");
		System.out.println(Thread.currentThread() .getName()+ " - task C");
		System.out.println(Thread.currentThread() .getName()+ " - task D");
	}
	
	private static void threading1() {
		Thread ta = new MyThread("A");
		Thread tb = new MyThread("B");
		Thread tc = new MyThread("C");
		Thread td = new MyThread("D");
		ta.start();
		tb.start();
		tc.start();
		td.start();
		
	}
	
	static class MyThread extends Thread {
		String taskName;
		
		MyThread(String taskName) {
			this.taskName = taskName;
		}
		
		@Override
		public void run() {
			try {
				// simulating heavy processing activities.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() .getName()+ " - task "+taskName);
		}
	}
	
	private static void threading2() {
		Thread ta = new Thread(new MyRunnable("T"));
		Thread tb = new Thread(new MyRunnable("U"));
		Thread tc = new Thread(new MyRunnable("V"));
		Thread td = new Thread(new MyRunnable("W"));
		ta.start();
		tb.start();
		tc.start();
		td.start();
	}
	
	static class MyRunnable implements Runnable {
		String taskName;
		
		MyRunnable(String taskName) {
			this.taskName = taskName;
		}
		
		@Override
		public void run() {
			try {
				// simulating heavy processing activities.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() .getName()+ " - task "+taskName);
		}
	}
	
	private static void threading3() {
		Thread ta = new Thread(new MyRunnable("T"));
		Thread tb = new Thread(new MyRunnable("U"));
		Thread tc = new Thread(new MyRunnable("V"));
		Thread td = new Thread(new MyRunnable("W"));
		
		ta.setDaemon(true);
		tb.setDaemon(true);
		tc.setDaemon(true);
		td.setDaemon(true);
		
		ta.start();
		tb.start();
		tc.start();
		td.start();
		
	}

}
