package edu.drexel.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.drexel.domain.BankAccount;

public class BankAccountRepoImpl extends AbstractRepoImpl<BankAccount> implements BankAccountRepo {

	public List<BankAccount> getAll() {
		String sql = "SELECT * FROM bank_account";
		return getAll(sql, (rs) -> mapRow(rs) );
	}

	private BankAccount mapRow(ResultSet rs) throws SQLException {
		//extract and populate DB data, map to an bankAccount, and append to list
		BankAccount bankAccount = new BankAccount(); 
		bankAccount.setAccountId(rs.getInt("account_id"));
		bankAccount.setAccountType(rs.getString("account_type"));
		bankAccount.setBalance(rs.getInt("balance"));
		return bankAccount;
	}
	
	public BankAccount findByID(int id) {
		String sql = "SELECT * FROM bank_account WHERE account_id = ?";
		System.out.println("sql = " + sql);
		return findById(sql, id, (rs) -> mapRowEx(rs) );
	}

	private BankAccount mapRowEx(ResultSet rs) throws SQLException {
		BankAccount bankAccount;
		//extract and populate DB data, map to an bankAccount, and append to list
		bankAccount = new BankAccount(); 
		bankAccount.setAccountId(rs.getInt("account_id"));
		bankAccount.setAccountType(rs.getString("account_type"));
		bankAccount.setBalance(rs.getInt("balance"));
		return bankAccount;
	}

	public int insert(BankAccount bankAccount) {
		String sql = "INSERT INTO bank_account (account_id, account_type, balance) " + 
				"VALUES (?, ?, ?)";
		return insert(sql, bankAccount, (bankAccount1, pstmt) -> setInsertParams(bankAccount1, pstmt) );
	}
	
	public int insertList(List<BankAccount> bankAccountList) {
		String sql = "INSERT INTO bank_account (account_id, account_type, balance) " + 
				"VALUES (?, ?, ?)";
		return insertList(sql, bankAccountList, (bankAccount1, pstmt) -> setInsertParams(bankAccount1, pstmt) );
	}

	@Override
	protected int handleInsertKey(BankAccount bankAccount, int bankAccountId, PreparedStatement pstmt)
			throws SQLException {
		try (ResultSet resultSet = pstmt.getGeneratedKeys();) {
			if (resultSet.next()) {
				bankAccountId = resultSet.getInt(1);
				bankAccount.setAccountId(bankAccountId);
			}
		}
		return bankAccountId;
	}

	private void setInsertParams(BankAccount bankAccount, PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1,  bankAccount.getAccountId());
		pstmt.setString(2, bankAccount.getAccountType());
		pstmt.setDouble(3,  bankAccount.getBalance());
	}

	public int update(BankAccount bankAccount) {
		String sql = "UPDATE bank_account " +
				" SET account_type=?, balance=?"+ 
				"WHERE account_id = ?";
		return update(sql, bankAccount, (bankAccount1, pstmt) -> setUpdateParams(bankAccount1, pstmt) );
	}

	private void setUpdateParams(BankAccount bankAccount, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, bankAccount.getAccountType());
		pstmt.setDouble(2,  bankAccount.getBalance());
		pstmt.setInt(3, bankAccount.getAccountId());
	}

	public int delete(int id) {
		String sql = "DELETE FROM bank_account WHERE account_id = ?";
		return delete(sql, id);
	}
	
	public int[] transfer(double amount, int fromAccount, int toAccount) {
		int[] rowAffected = new int[2];
		
		registerDriver();
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement pstmt = connection.prepareStatement("UPDATE bank_account " +
						" SET balance=?"+ 
						"WHERE account_id = ?");
				PreparedStatement pstmt2 = connection.prepareStatement("UPDATE bank_account " +
						" SET balance=?"+ 
						"WHERE account_id = ?");
				) {

			try {
				connection.setAutoCommit(false);     // begin tran

				BankAccount sender = this.findByID(fromAccount);
				BankAccount receiver = this.findByID(toAccount);

				//set parameters for prepared statement 
				pstmt.setDouble(1, sender.getBalance() - amount );
				pstmt.setInt(2, sender.getAccountId());

				// execute query  1.
				rowAffected[0] = pstmt.executeUpdate();

				pstmt2.setDouble(1, receiver.getBalance() + amount );
				pstmt2.setInt(2, receiver.getAccountId());

				// execute query 2.
				rowAffected[1] =  pstmt2.executeUpdate();

				connection.commit();     // commit tran
				System.out.println("Successfully updated and committed.");
			} catch (Exception e) {
				try {
					System.out.println("WARNING: rolling back the transaction");
					connection.rollback();
					System.out.println("Successfully rollbacked.");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Internal failure with db rollback. " + e.getMessage());
					e1.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rowAffected;
	}


}
