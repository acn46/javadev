package edu.drexel.repo;

import java.util.List;

import edu.drexel.domain.BankAccount;

public interface BankAccountRepo {
	List<BankAccount> getAll();
	BankAccount findByID(int id);
	int insert(BankAccount bankAccount);
	int insertList(List<BankAccount> bankAccountList);
	int update(BankAccount bankAccount);
	int delete(int id);
	int[] transfer(double amount, int fromAccount, int toAccount);
}
