package edu.drexel.JdbcSample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import edu.drexel.domain.BankAccount;
import edu.drexel.repo.BankAccountRepo;
import edu.drexel.repo.BankAccountRepoImpl;

/**
 * JdbcSample app.
 *
 */
public class AppEx {
    private static final int NBR_OF_INSERTS = 5000;
    private static int startKounter = 6;

	public static void main( String[] args ) {
        System.out.println( "App - starting ..." );
        long startTime = System.currentTimeMillis();
        
        int nbrOfThreads = 25;
		ExecutorService executor = Executors.newFixedThreadPool(nbrOfThreads);     // create a thread pool.
		CompletionService<Integer> executorService = new ExecutorCompletionService<>(executor);  // a service for code to use.
		
		// define the task and submit them to executorService
		System.out.println("Submitting tasks for inserting accounts.");
		for (int i = 0; i < NBR_OF_INSERTS; i++) {
			final int k = i;
			Callable<Integer> task = new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					int accountId = insertBankAccount(k);
					return accountId;
				}
			};
			
			Future<Integer> future = executorService.submit(task);
			
			if (i % 1000 == 0) {
				System.out.println("Submitted "+i+" accounts.");
			}
		}
		
		// get results from executorService.
		System.out.println("Getting results from insertions.");
		try {
			List<Integer> results = new ArrayList<>();
			for (int i = 0; i < NBR_OF_INSERTS; i++) {
				Future<Integer> future = executorService.take();
				Integer accountId = future.get();
				results.add(accountId);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// shutdown
		System.out.println("Shutting down execution threads.");
		executor.shutdown();
		try {
			// allow existing tasks some time to complete.
			if (! executor.awaitTermination(60, TimeUnit.SECONDS)) {
				System.out.println("WARNING: forceful shutdown.");
				executor.shutdownNow();     // cancel current executing tasks
				if (! executor.awaitTermination(10, TimeUnit.SECONDS)) {
					System.err.println("WARNING: executor/pool did not terminate.");
				}
			} else {
				System.out.println("normal shutdown completed.");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
