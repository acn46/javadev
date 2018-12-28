package edu.drexel.repo;

import java.util.List;

import edu.drexel.domain.Address;

public interface AddressRepo {
	List<Address> getAll();
	Address findByID(int id);
	int insert(Address address);
	int update(Address address);
	int delete(int id);
}
