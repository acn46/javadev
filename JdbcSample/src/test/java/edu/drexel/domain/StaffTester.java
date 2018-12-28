package edu.drexel.domain;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import edu.drexel.domain.Staff;

public class StaffTester {

	@Test
	public void test() {
		Staff staff1 = new Staff();
		staff1.setFirstName("Alan");
		staff1.setLastName("Nguyen");
		
		Staff staff2 = new Staff();
		staff2.setFirstName("Alan");
		staff2.setLastName("Nguyen");
		
		Staff staff3 = staff1;
		
		// the == operator is for checking identity.
		System.out.println("");
		if (staff1 == staff2) {
			System.out.println("1 & 2 are same");
		} else {
			System.out.println("1 & 2 are not the same");     // answer!
		}
		
		if (staff1 == staff3) {
			System.out.println("1 & 3 are same");             // answer!
		} else {
			System.out.println("1 & 3 are not the same");
		}
		
		// the equals() method is for checking logical equality.
		System.out.println("");
		if (staff1.equals(staff2)) {
			System.out.println("1 & 2 are equal");     // answer ?
		} else {
			System.out.println("1 & 2 are not equal");
		}
		
		if (staff1.equals(staff3)) {
			System.out.println("1 & 3 are equal");     // answer!
		} else {
			System.out.println("1 & 3 are not equal");
		}
		
		
		Set<Staff> set = new HashSet();
		set.add(staff1);
		set.add(staff2);
		set.add(staff3);
		System.out.println("");
		System.out.println("The set has "+set.size()+" unique items.");
		
	}

}
