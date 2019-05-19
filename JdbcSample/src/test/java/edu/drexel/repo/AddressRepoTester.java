package edu.drexel.repo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.drexel.domain.Address;
import edu.drexel.repo.AddressRepo;
import edu.drexel.repo.AddressRepoImpl;

public class AddressRepoTester {

	@Test
	public void test_getAll() {
		System.out.println("\ntest getAll");
		AddressRepo dao = new AddressRepoImpl();
		List<Address> list = dao .getAll();
		System.out.println("# of rows " + list.size());
//		for (Address address : list) {
//			System.out.println(address);
//		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void test_findByID() {
		System.out.println("\ntest findByID");
		AddressRepo dao = new AddressRepoImpl();
		Address address = dao.findByID(1);
		System.out.println(address);
		assertTrue(address != null && address.getId() == 1);
	}

	@Test
	public void test_findByID_notFound() {
		System.out.println("\ntest findbyID_notFound");
		AddressRepo dao = new AddressRepoImpl();
		Address address = dao.findByID(-1);
		System.out.println(address);
		assertTrue(address == null);
	}
	
	@Test
	public void test_insert() {
		System.out.println("\ntest Insert");
		AddressRepo dao = new AddressRepoImpl();
		Address address = new Address("address1test", "address2test", "districttest", 1, "postalCode", "PhoneTest", null, null);
		int addressId = dao.insert(address);
		System.out.println(addressId);
		assertTrue(addressId > 0);
	}
	
	@Test
	public void test_update() {
		System.out.println("\ntest update");
		AddressRepo dao = new AddressRepoImpl();
		Address address = new Address("address1test", "address2test", "districttest", 1, "postalCode", "PhoneTest", null, null);
		address.setId(611);
		int rowAffected = dao.update(address);
		System.out.println(rowAffected);
		//assertTrue(rowAffected == 1);
	}
	
	@Test
	public void test_delete() {
		System.out.println("\ntest delete");
		AddressRepo dao = new AddressRepoImpl();
		int rowAffected = dao.delete(607);
		System.out.println(rowAffected);
		//assertTrue(rowAffected == 1);
	}
	
	
}
