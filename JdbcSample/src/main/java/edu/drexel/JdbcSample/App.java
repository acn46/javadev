package edu.drexel.JdbcSample;

import edu.drexel.domain.BankAccount;
import edu.drexel.repo.BankAccountRepo;
import edu.drexel.repo.BankAccountRepoImpl;

/**
 * JdbcSample app.
 *
 */
public class App {
    private static final int NBR_OF_INSERTS = 5000;
    private static int startKounter = 6;

	public static void main( String[] args ) {
        System.out.println( "App - starting ..." );
        long startTime = System.currentTimeMillis();
		for (int i = 0; i < NBR_OF_INSERTS; i++) {
			int accountId = insertBankAccount(i);
			
			if (i % 1000 == 0) {
				System.out.println("Inserted "+i+" accounts.");
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Elapsed time: "+ ((endTime - startTime) / 1000) + " seconds.");
    }

	private static int insertBankAccount(int i) {
		BankAccountRepo dao = new BankAccountRepoImpl();
		String type = ((i % 2) == 0) ? "checking" : "saving" ;
		BankAccount bankAccount = new BankAccount(startKounter + i, type, (startKounter + i) * 100.00 );
		int bankAccountId = dao.insert(bankAccount);
		return bankAccountId;
	}
}
