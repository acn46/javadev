package edu.drexel.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyOnePerThreadTester {

	@Test
	public void test1() {
		System.out.println("\nIn test1:");
		MyOnePerThread obj1 = MyOnePerThread.getInstance();     // from current thread.
		MyOnePerThread obj2 = MyOnePerThread.getInstance();     // from current thread.
		MyOnePerThread obj3 = MyOnePerThread.getInstance();     // from current thread.
		
		assertTrue(obj1 == obj2 && obj1 == obj3);	
	}
	
	@Test
	public void test2() {
		System.out.println("\nIn test2");
		MyOnePerThread obj1 = MyOnePerThread.getInstance();     // from current thread.
		MyOnePerThread obj2 = getInstanceFromAnotherThread();   // from another thread.
		MyOnePerThread obj3 = getInstanceFromAnotherThread();   // from yet another thread.
		
		assertTrue(obj1 != obj2 && obj2 != obj3);	
	}
	
	static class MyThread extends Thread {
		MyOnePerThread obj;
		@Override
		public void run() {
			obj = MyOnePerThread.getInstance();
		}
	}
	
	private static MyOnePerThread getInstanceFromAnotherThread() {
		MyThread thread = new MyThread();
		thread.start();
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return thread.obj;
	}

}
