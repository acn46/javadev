package edu.drexel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;
import edu.drexel.model.Staff;

public class StaffDAOImpl implements StaffDAO {
	public static final String URL = "jdbc:mysql://localhost:3306/sakila";
	public static final String USER = "user1";
	public static final String PWD = "password";
	//open a connection to the server
	public List<Staff> getAll() {
		List<Staff> list = new ArrayList();
		try {
			DriverManager.registerDriver(new Driver());
			Connection connection = DriverManager.getConnection(URL,  USER, PWD);
			
			//SQL statement stored in string 
			String sql = "SELECT * FROM STAFF";
			Statement stmt = connection.createStatement(); //statement can be created from string
			ResultSet rs = stmt.executeQuery(sql); //query can be executed  
			
			while (rs.next()) { //rs.next() returns true or false, similar to text I/O
				Staff staff = new Staff();
				staff.setStaffId(rs.getInt("staff_id"));
				staff.setFirstName(rs.getString("firstName"));
				staff.setLastName(rs.getString("lastName"));
				staff.setAddressId(rs.getInt("address_id"));
				//staff.setFirstName(rs.getString("picture"));
				staff.setEmail(rs.getString("email"));
				staff.setStoreId(rs.getInt("store_id"));
				staff.setActive(rs.getInt("active"));
				staff.setUsername(rs.getString("username"));
				staff.setPassword(rs.getString("password"));
				staff.setLastUpdate(rs.getDate("last_update"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public Staff findById(int id) {
		
		Staff staff = null;
		try {
			DriverManager.registerDriver(new Driver());
			Connection connection = DriverManager.getConnection(URL,  USER, PWD);
			
			//SQL statement stored in string 
			String sql = "SELECT * FROM STAFF where staff_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql); //prepare a statement instead of creating to allow for parameter
			
			stmt.setInt(1, id);
			
			//query is still executed as with created statement 
			ResultSet rs = stmt.executeQuery(sql);  
			
			if (rs.next()) { //rs.next() returns true or false, similar to text I/O
				staff = new Staff();
				
				staff.setStaffId(rs.getInt("staff_id"));
				staff.setFirstName(rs.getString("firstName"));
				staff.setLastName(rs.getString("lastName"));
				staff.setAddressId(rs.getInt("address_id"));
				//staff.setFirstName(rs.getString("picture"));
				staff.setEmail(rs.getString("email"));
				staff.setStoreId(rs.getInt("store_id"));
				staff.setActive(rs.getInt("active"));
				staff.setUsername(rs.getString("username"));
				staff.setPassword(rs.getString("password"));
				staff.setLastUpdate(rs.getDate("last_update"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return staff;
	}

	public int insert(Staff staff) {
		
		int id = 0;
		
		try {
			//get a db connection
			DriverManager.registerDriver(new Driver());
			Connection connection = DriverManager.getConnection(URL,  USER, PWD);
			
			//define query and execute query
			String sql = "INSERT INTO staff (first_name, last_name, address_id, email, store_id, active, username, password, last_update) " + 
						"VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURDATE())";
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//set parameters for prepared statement 
			pstmt.setString(1,  staff.getFirstName());
			pstmt.setString(2, staff.getLastName());
			pstmt.setInt(3,  staff.getAddressId());
			pstmt.setString(4, staff.getEmail());
			pstmt.setInt(5,  staff.getStoreId());
			pstmt.setInt(6, staff.getActive());
			pstmt.setString(7, staff.getUsername());
			pstmt.setString(8, staff.getPassword());
			
			//execute query
			int rowAffected = pstmt.executeUpdate();
			
			if (rowAffected == 1) {
				ResultSet resultSet = pstmt.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
					staff.setStaffId(id);
				}
			} 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}

	public int update(Staff staff) {
		int rowAffected = 0;
		
		try {
			//get a db connection
			DriverManager.registerDriver(new Driver());
			Connection connection = DriverManager.getConnection(URL,  USER, PWD);
			
			//define query and execute query
			String sql = "UPDATE staff" + 
						" SET first_name = ?, last_name = ?, address_id = ?, picture = NULL, email = ?, store_id = ?, active = ?, username = ?, passowrd = ?, last_update =CURDATE() WHERE staff_id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			//set parameters for prepared statement 
			pstmt.setString(1,  staff.getFirstName());
			pstmt.setString(2, staff.getLastName());
			pstmt.setInt(3,  staff.getAddressId());
			pstmt.setString(4, staff.getEmail());
			pstmt.setInt(5,  staff.getStoreId());
			pstmt.setInt(6, staff.getActive());
			pstmt.setString(7, staff.getUsername());
			pstmt.setString(8, staff.getPassword());
			pstmt.setInt(9, staff.getStaffId());
			
			//execute query
			rowAffected = pstmt.executeUpdate();
			

			} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowAffected;
	} 
	
		

	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}