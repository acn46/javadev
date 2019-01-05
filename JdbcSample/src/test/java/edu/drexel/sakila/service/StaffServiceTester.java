package edu.drexel.sakila.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import edu.drexel.domain.Staff;

public class StaffServiceTester {

	@Test
	public void test_getAll() {
		System.out.println("\ntest getAll");
		StaffService svc = new StaffService();
		List<Staff> list = svc.getAll();
		System.out.println("# of rows " + list.size());
		for (Staff staff : list) {
			System.out.println(staff);
		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void test_findByID() {
		System.out.println("\ntest findByID");
		StaffService svc = new StaffService();
		Staff staff = svc.findByID(1);
		System.out.println(staff);
		assertTrue(staff != null && staff.getStaffId() == 1);
	}
	
	@Test
	public void test_insert() {
		System.out.println("\ntest Insert");
		StaffService svc = new StaffService();
		Staff staff = new Staff("John", "Doe", 1, "John@compmail.com", null, 2, 1, "JDoe", "dwe23321", null);
		int staffId = svc.insert(staff);
		System.out.println(staffId);
		assertTrue(staffId > 0);
	}
	
	@Test
	public void test_update() {
		System.out.println("\ntest update");
		StaffService svc = new StaffService();
		Staff staff = new Staff("John1", "Doe", 1, "John@compmail.com", null, 2, 1, "JDoe", "dwe23321", null);
		staff.setStaffId(10);
		int rowAffected = svc.update(staff);
		System.out.println(rowAffected);
		assertTrue(rowAffected == 1);
	}
	
	@Test
	public void test_delete() {
		System.out.println("\ntest delete");
		StaffService svc = new StaffService();
		int rowAffected = svc.delete(5);
		System.out.println(rowAffected);
		//assertTrue(rowAffected == 1);
	}
}
