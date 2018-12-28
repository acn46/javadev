package edu.drexel.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.drexel.model.Address;

public interface AddressDAO {
	static Map<Integer, Address> ADDRESS_MAP = new HashMap<>();
	
	List<Address> getAll();
	Address findByID(int id);
	int insert(Address address);
	int update(Address address);
	int delete(int id);
}
