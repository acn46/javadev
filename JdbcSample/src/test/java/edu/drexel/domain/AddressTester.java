package edu.drexel.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.drexel.domain.Address;

public class AddressTester {

	@Test
	public void test_createAddress() {
		Address address = new Address();
		//set fields
		address.setAddress("address line 1");
		address.setAddress2("address line 2");
		//...  
		
		// get fields
		String addressLine1 = address.getAddress();
		String addressLine2 = address.getAddress2();
		
		//print 
		System.out.println(address);
		
		//verify
		assertTrue(addressLine1.equals("address line 1"));
		assertTrue(addressLine2.equals("address line 2"));
	}

}
