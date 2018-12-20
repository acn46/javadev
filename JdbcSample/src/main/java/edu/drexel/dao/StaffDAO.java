package edu.drexel.dao;

import java.util.List;

public interface StaffDAO {
	List<Staff> getAll();
	Staff findById(int id);
	int insert(Staff staff);
	int update(Staff staff);
	int delete(int id);
}
