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
	public static final String USER = "root";
	public static final String PWD = "password";

	@Override
	public final List<T> getAll(String sql, RowMapper<T> rowMapper) {
		List<T> list = new ArrayList<>();
		
		registerDriver();
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				) {
			
			while (rs.next()) {
//				T domain = mapRow(rs);
				T domain = rowMapper.mapRow(rs);
				list.add(domain);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

//	// subclass need to provide impl for this.
//	protected abstract T mapRow(ResultSet rs) throws SQLException;

	protected void registerDriver() {
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public final T findById(String sql, int id, RowMapper<T> rowMapper) {
		registerDriver();
		
		T domain = null;
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement stmt = connection.prepareStatement(sql);
				) {
			
			stmt.setInt(1, id);
			
			try (ResultSet rs = stmt.executeQuery();) {
				if (rs.next()) {
//					domain = mapRowEx(rs);
					domain = rowMapper.mapRow(rs);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return domain;
	}

//	protected abstract T mapRowEx(ResultSet rs) throws SQLException;

	@Override
	public final int insert(String sql, T domain, ParamSetter<T> paramSetter) {
		
		long t1 = System.currentTimeMillis();
		
		registerDriver();
		
		long t2 = System.currentTimeMillis();
		System.out.println("t2 = " + (t2 -t1));
		
		int id = 0;
		
		try ( Connection connection = DriverManager.getConnection(URL,  USER, PWD);
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				) {
			
			long t3 = System.currentTimeMillis();
			System.out.println("t3 = " + (t3 -t2));
			
			//set parameters for prepared statement 
//			setInsertParams(domain, pstmt);
			paramSetter.setParams(domain, pstmt);
			
			//execute query
			int rowAffected = pstmt.executeUpdate();
			
			if (rowAffected == 1) {
				id = handleInsertKey(domain, id, pstmt);
			} 
			
			long t4 = System.currentTimeMillis();
			System.out.println("t4= " + (t4 -t3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public final int insertList(String sql, List<T> domainList, ParamSetter<T> paramSetter) {
		
		long t1 = System.currentTimeMillis();
		
		registerDriver();
		
		long t2 = System.currentTimeMillis();
		System.out.println("t2 = " + (t2 -t1));
		
		int id = 0;
		final int BATCH_SIZE = 1000;
		try ( Connection connection = DriverManager.getConnection(URL,  USER, PWD);
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				) {
			
			long t3 = System.currentTimeMillis();
			System.out.println("t3 = " + (t3 -t2));
			
			int k = 0;
			//for (T domain : domainList) {
			for (int i = 0; i < domainList.size(); i++) {
				T domain = domainList.get(i);
				
				//set parameters for prepared statement 
				//			setInsertParams(domain, pstmt);
				paramSetter.setParams(domain, pstmt);
				
				//batch 
				
				pstmt.addBatch();
				//execute query
				//int rowAffected = pstmt.executeUpdate();
				
				//if (rowAffected == 1) {
				//	id = handleInsertKey(domain, id, pstmt);
				//} 
				
				k++;
				if (i % BATCH_SIZE == BATCH_SIZE - 1) {
					pstmt.executeBatch();
					k = 0;
				}
			}
			
			//Leftover from loops
			if (k != 0) {
				pstmt.executeBatch();
			}
			
			//int[] rowAffected = pstmt.executeBatch();
			
			long t4 = System.currentTimeMillis();
			System.out.println("t4= " + (t4 -t3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
	
//	protected abstract void setInsertParams(T domain, PreparedStatement pstmt) throws SQLException;
	
	protected abstract int handleInsertKey(T domain, int id, PreparedStatement pstmt) throws SQLException;
	
	public final int update(String sql, T domain, ParamSetter<T> paramSetter) {
		int rowAffected = 0;
		
		registerDriver();
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement pstmt = connection.prepareStatement(sql);
				) {
			
			//set parameters for prepared statement 
//			setUpdateParams(domain, pstmt);
			paramSetter.setParams(domain, pstmt);
			
			//execute query
			rowAffected = pstmt.executeUpdate();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return rowAffected;
	}

//	protected abstract void setUpdateParams(T domain, PreparedStatement pstmt) throws SQLException;
	
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
