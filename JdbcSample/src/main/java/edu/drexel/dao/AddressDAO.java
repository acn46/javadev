package edu.drexel.dao;

import java.util.List;

import edu.drexel.model.Address;

public interface AddressDAO {
	List<Address> getAll();
	Address findByID(int id);
	int insert(Address address);
	int update(Address address);
	int delete(int id);
}
