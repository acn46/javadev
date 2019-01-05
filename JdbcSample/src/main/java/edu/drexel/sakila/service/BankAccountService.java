package edu.drexel.sakila.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import edu.drexel.domain.BankAccount;
import edu.drexel.repo.BankAccountRepo;
import edu.drexel.repo.BankAccountRepoImpl;

@Path("/bankaccounts")
public class BankAccountService {
	
	@GET
	public List<BankAccount> getAll() {
		System.out.println("\nin getAll");
		BankAccountRepo dao = new BankAccountRepoImpl();
		List<BankAccount> list = dao .getAll();
		return list;
	}
	
	@GET
	@Path("/{id}")
	public BankAccount findByID(@PathParam("id") int id) {
		System.out.println("\nin findByID");
		BankAccountRepo dao = new BankAccountRepoImpl();
		BankAccount bankAccount = dao.findByID(id);
		System.out.println("bankAccount = " + bankAccount);
		return bankAccount;
		}

	@PUT
	public int insert(BankAccount bankAccount) {
		System.out.println("\nin Insert");
		BankAccountRepo dao = new BankAccountRepoImpl();
		//BankAccount bankAccount = new BankAccount(5, "checking", 13321.22);
		int bankAccountId = dao.insert(bankAccount);
		return bankAccountId;
	}
	
	@POST
	public int update(BankAccount bankAccount) {
		System.out.println("\nin update");
		BankAccountRepo dao = new BankAccountRepoImpl();
		//BankAccount bankAccount = new BankAccount(4, "checking", 10000);
		//bankAccount.setAccountId(5);
		int rowAffected = dao.update(bankAccount);
		return rowAffected;
	}
	
	@DELETE
	@Path("/{id}")
	public int delete(@PathParam("id") int id) {
		System.out.println("\nin delete");
		BankAccountRepo dao = new BankAccountRepoImpl();
		int rowAffected = dao.delete(id);
		return rowAffected;
	}
	
	//@POST
	public void transfer(float amount, int accountFrom, int accountTo) {
		System.out.println("\ntest transfer");
		BankAccountRepo dao = new BankAccountRepoImpl();
		int[] rowAffected = dao.transfer(amount, accountFrom, accountTo);
				System.out.println(rowAffected[0] + ", " + rowAffected[1]);
	}

}
