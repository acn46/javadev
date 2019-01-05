package edu.drexel.sakila.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import edu.drexel.domain.BankAccount;

public class BankAccountServiceTester {
	
	@Test
	public void test_getAll() {
		System.out.println("\ntest getAll");
		BankAccountService svc = new BankAccountService();
		List<BankAccount> list = svc .getAll();
		System.out.println("# of rows " + list.size());
		for (BankAccount bankAccount : list) {
			System.out.println(bankAccount);
		}
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void test_findByID() {
		System.out.println("\ntest findByID");
		BankAccountService svc = new BankAccountService();
		BankAccount bankAccount = svc.findByID(1);
		System.out.println(bankAccount);
		assertTrue(bankAccount != null && bankAccount.getAccountId() == 1);
	}

	@Test
	public void test_findByID_notFound() {
		System.out.println("\ntest findbyID_notFound");
		BankAccountService svc = new BankAccountService();
		BankAccount bankAccount = svc.findByID(-1);
		System.out.println(bankAccount);
		assertTrue(bankAccount == null);
	}
	
	@Test
	public void test_insert() {
		System.out.println("\ntest Insert");
		BankAccountService svc = new BankAccountService();
		BankAccount bankAccount = new BankAccount(5, "checking", 13321.22);
		int bankAccountId = svc.insert(bankAccount);
		System.out.println(bankAccountId);
		assertTrue(bankAccountId > 0);
	}
	
	@Test
	public void test_update() {
		System.out.println("\ntest update");
		BankAccountService svc = new BankAccountService();
		BankAccount bankAccount = new BankAccount(4, "checking", 10000);
		bankAccount.setAccountId(5);
		int rowAffected = svc.update(bankAccount);
		System.out.println(rowAffected);
		assertTrue(rowAffected == 1);
	}
	
	@Test
	public void test_delete() {
		System.out.println("\ntest delete");
		BankAccountService svc = new BankAccountService();
		int rowAffected = svc.delete(4);
		System.out.println(rowAffected);
		//assertTrue(rowAffected == 1);
	}
	
	@Test
	public void test_transfer() {
		System.out.println("\ntest transfer");
		BankAccountService svc = new BankAccountService();
		svc.transfer(4000, 5, 1);

	}

}
