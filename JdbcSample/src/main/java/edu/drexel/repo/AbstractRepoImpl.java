package edu.drexel.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

public abstract class AbstractRepoImpl<T> implements Repo<T> {
	public static final String URL = "jdbc:mysql://localhost:3306/sakila";
	public static final String USER = "user1";
	public static final String PWD = "password";

	public final List<T> getAll(String sql) {
		List<T> list = new ArrayList<>();
		
		registerDriver();
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {
			
			while (rs.next()) {
				T domain = mapRow(rs);
				list.add(domain);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	// subclass need to provide impl for this.
	protected abstract T mapRow(ResultSet rs) throws SQLException;

	protected void registerDriver() {
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public final T findById(String sql, int id) {
		registerDriver();
		
		T domain = null;
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement stmt = connection.prepareStatement(sql);
				) {
			
			stmt.setInt(1, id);
			
			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
					domain = mapRowEx(rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return domain;
	}

	protected abstract T mapRowEx(ResultSet rs) throws SQLException;

	public final int insert(String sql, T domain) {
		
		registerDriver();
		
		int id = 0;
		
		try ( Connection connection = DriverManager.getConnection(URL,  USER, PWD);
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				) {
			
			//set parameters for prepared statement 
			setInsertParams(domain, pstmt);
			
			//execute query
			int rowAffected = pstmt.executeUpdate();
			
			if (rowAffected == 1) {
				id = handleInsertKey(domain, id, pstmt);
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}

	protected abstract void setInsertParams(T domain, PreparedStatement pstmt) throws SQLException;
	
	protected abstract int handleInsertKey(T domain, int id, PreparedStatement pstmt) throws SQLException;
	
	public final int update(String sql, T domain) {
		int rowAffected = 0;
		
		registerDriver();
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement pstmt = connection.prepareStatement(sql);
				) {
			
			//set parameters for prepared statement 
			setUpdateParams(domain, pstmt);
			
			//execute query
			rowAffected = pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rowAffected;
	}

	protected abstract void setUpdateParams(T domain, PreparedStatement pstmt) throws SQLException;
	
	public final int delete(String sql, int id) {
		int rowAffected = 0;
		
		registerDriver();
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement pstmt = connection.prepareStatement(sql);
				) {
			
			//set parameters for prepared statement 
			pstmt.setInt(1, id);
			
			//execute query
			rowAffected = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rowAffected;
	}

}
