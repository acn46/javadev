package edu.drexel.repo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import edu.drexel.domain.BankAccount;
import edu.drexel.repo.BankAccountRepo;


public class BankAccountRepoTester {

	@Test
	public void test_getAll() {
		System.out.println("\ntest getAll");
		BankAccountRepo dao = new BankAccountRepoImpl();
		List<BankAccount> list = dao .getAll();
		System.out.println("# of rows " + list.size());
		for (BankAccount bankAccount : list) {
			System.out.println(bankAccount);
		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void test_findByID() {
		System.out.println("\ntest findByID");
		BankAccountRepo dao = new BankAccountRepoImpl();
		BankAccount bankAccount = dao.findByID(1);
		System.out.println(bankAccount);
		assertTrue(bankAccount != null && bankAccount.getAccountId() == 1);
	}

	@Test
	public void test_findByID_notFound() {
		System.out.println("\ntest findbyID_notFound");
		BankAccountRepo dao = new BankAccountRepoImpl();
		BankAccount bankAccount = dao.findByID(-1);
		System.out.println(bankAccount);
		assertTrue(bankAccount == null);
	}
	
//	@Test
//	public void test_insert() {
//		System.out.println("\ntest Insert");
//		BankAccountRepo dao = new BankAccountRepoImpl();
//		BankAccount bankAccount = new BankAccount(5, "checking", 13321.22);
//		int bankAccountId = dao.insert(bankAccount);
//		System.out.println(bankAccountId);
//		assertTrue(bankAccountId > 0);
//	}
	
//	@Test
//	public void test_update() {
//		System.out.println("\ntest update");
//		BankAccountRepo dao = new BankAccountRepoImpl();
//		BankAccount bankAccount = new BankAccount(4, "checking", 10000);
//		bankAccount.setAccountId(5);
//		int rowAffected = dao.update(bankAccount);
//		System.out.println(rowAffected);
//		assertTrue(rowAffected == 1);
//	}
	
//	@Test
//	public void test_delete() {
//		System.out.println("\ntest delete");
//		BankAccountRepo dao = new BankAccountRepoImpl();
//		int rowAffected = dao.delete(4);
//		System.out.println(rowAffected);
//		//assertTrue(rowAffected == 1);
//	}
	
	@Test
	public void test_transfer() {
		System.out.println("\ntest transfer");
		BankAccountRepo dao = new BankAccountRepoImpl();
		int[] rowAffected = dao.transfer(4000, 5, 1);
				System.out.println(rowAffected[0] + ", " + rowAffected[1]);
	}
	
	
}
