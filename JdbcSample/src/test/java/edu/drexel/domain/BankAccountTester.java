package edu.drexel.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.drexel.domain.BankAccount;

public class BankAccountTester {

	@Test
	public void test_createBankAccount() {
		BankAccount bankAccount = new BankAccount();
		//set fields
		bankAccount.setAccountId(23);
		bankAccount.setAccountType("checking");
		bankAccount.setBalance(5000);
		//...  
		
		// get fields
		int bankAccountLine1 = bankAccount.getAccountId();
		double bankAccountLine2 = bankAccount.getBalance();
		
		//print 
		System.out.println(bankAccount);
		
		//verify
		assertTrue(bankAccountLine1 == 23);
		assertTrue(bankAccountLine2 == 5000);
	}

}
