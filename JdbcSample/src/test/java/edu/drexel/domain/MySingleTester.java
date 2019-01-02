package edu.drexel.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class MySingleTester {

	@Test
	public void test() {
		MySingle obj1 = MySingle.getInstance();
		MySingle obj2 = MySingle.getInstance();
		
		assertTrue(obj1 == obj2);		
	}

}
