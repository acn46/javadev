package edu.drexel.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyFactoryTester {

	@Test
	public void test1() {
		System.out.println("\nIn test1:");
		MyFactory obj1 = MyFactory.getInstance();     // from current thread.
		MyFactory obj2 = MyFactory.getInstance();     // from current thread.
		MyFactory obj3 = MyFactory.getInstance();     // from current thread.
		for (int i=0; i < 14; i++) {
			MyFactory obj = MyFactory.getInstance();
		}
		
		assertTrue(obj1 != obj2 && obj2 != obj3);	
	}

}
