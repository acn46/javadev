package edu.drexel.sakila.service;

import java.util.List;

import edu.drexel.domain.Staff;
import edu.drexel.repo.StaffRepo;
import edu.drexel.repo.StaffRepoImpl;

public class StaffService {
	
	public List<Staff> getAll() {
		System.out.println("\nin getAll");
		StaffRepo dao = new StaffRepoImpl();
		List<Staff> list = dao.getAll();
		return list;

	}
	
	public Staff findByID(int id) {
		System.out.println("\nin findByID");
		StaffRepo dao = new StaffRepoImpl();
		Staff staff = dao.findById(id);
		return staff;
	}
	
	public int insert(Staff staff) {
		System.out.println("\nin Insert");
		StaffRepo dao = new StaffRepoImpl();
		//Staff staff = new Staff("John", "Doe", 1, "John@compmail.com", null, 2, 1, "JDoe", "dwe23321", null);
		int staffId = dao.insert(staff);
		return staffId;
	}
	
	public int update(Staff staff) {
		System.out.println("\nin update");
		StaffRepo dao = new StaffRepoImpl();
		//Staff staff = new Staff("John1", "Doe", 1, "John@compmail.com", null, 2, 1, "JDoe", "dwe23321", null);
		//staff.setStaffId(10);
		int rowAffected = dao.update(staff);
		return rowAffected;
	}
	
	public int delete(int id) {
		System.out.println("\nin delete");
		StaffRepo dao = new StaffRepoImpl();
		int rowAffected = dao.delete(id);
		return rowAffected;
	}

}
