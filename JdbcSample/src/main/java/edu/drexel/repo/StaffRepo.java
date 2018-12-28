package edu.drexel.repo;

import java.util.List;

import edu.drexel.domain.Staff;

public interface StaffRepo {
	List<Staff> getAll();
	Staff findById(int id);
	int insert(Staff staff);
	int update(Staff staff);
	int delete(int id);
}
