package edu.drexel.dao;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import edu.drexel.model.Staff;

public class StaffDAOTester {

	@Test
	public void test_getAll() {
		System.out.println("\ntest getAll");
		StaffDAO dao = new StaffDAOImpl();
		List<Staff> list = dao.getAll();
		System.out.println("# of rows " + list.size());
		for (Staff staff : list) {
			System.out.println(staff);
		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void test_findByID() {
		System.out.println("\ntest findByID");
		StaffDAO dao = new StaffDAOImpl();
		Staff staff = dao.findById(1);
		System.out.println(staff);
		assertTrue(staff != null && staff.getStaffId() == 1);
	}

	@Test
	public void test_findByID_notFound() {
		System.out.println("\ntest findbyID_notFound");
		StaffDAO dao = new StaffDAOImpl();
		Staff staff = dao.findById(-1);
		System.out.println(staff);
		assertTrue(staff == null);
	}
	
	@Test
	public void test_insert() {
		System.out.println("\ntest Insert");
		StaffDAO dao = new StaffDAOImpl();
		Staff staff = new Staff("John", "Doe", 1, "John@compmail.com", null, 2, 1, "JDoe", "dwe23321", null);
		int staffId = dao.insert(staff);
		System.out.println(staffId);
		assertTrue(staffId > 0);
	}
	
	@Test
	public void test_update() {
		System.out.println("\ntest update");
		StaffDAO dao = new StaffDAOImpl();
		Staff staff = new Staff("John1", "Doe", 1, "John@compmail.com", null, 2, 1, "JDoe", "dwe23321", null);
		staff.setStaffId(4);
		int rowAffected = dao.update(staff);
		System.out.println(rowAffected);
		assertTrue(rowAffected == 1);
	}
	
	@Test
	public void test_delete() {
		System.out.println("\ntest delete");
		StaffDAO dao = new StaffDAOImpl();
		int rowAffected = dao.delete(4);
		System.out.println(rowAffected);
		//assertTrue(rowAffected == 1);
	}
	
}