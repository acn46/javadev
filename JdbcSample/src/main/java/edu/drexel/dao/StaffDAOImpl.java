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

import edu.drexel.domain.Staff;

public class StaffDAOImpl implements StaffDAO {
	public static final String URL = "jdbc:mysql://localhost:3306/sakila";
	public static final String USER = "user1";
	public static final String PWD = "password";
	
	//open a connection to the server
	public List<Staff> getAll() {
		List<Staff> list = new ArrayList();
		
		registerDriver();
		
		try ( Connection connection = DriverManager.getConnection(URL,  USER, PWD); 
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM staff");
				) {
			
			
			//SQL statement stored in string 
		
			 //statement can be created from string
			 //query can be executed  
			
			while (rs.next()) { //rs.next() returns true or false, similar to text I/O
				Staff staff = new Staff();
				staff.setStaffId(rs.getInt("staff_id"));
				staff.setFirstName(rs.getString("first_Name"));
				staff.setLastName(rs.getString("last_Name"));
				staff.setAddressId(rs.getInt("address_id"));
				//staff.setFirstName(rs.getString("picture"));
				staff.setEmail(rs.getString("email"));
				staff.setStoreId(rs.getInt("store_id"));
				staff.setActive(rs.getInt("active"));
				staff.setUsername(rs.getString("username"));
				staff.setPassword(rs.getString("password"));
				staff.setLastUpdate(rs.getDate("last_update"));
				list.add(staff);			
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	private void registerDriver() {
		try {
			DriverManager.registerDriver(new Driver());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public Staff findById(int id) {
		registerDriver();
		
		Staff staff = null;
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD); 
			 PreparedStatement stmt = connection.prepareStatement("SELECT * FROM staff WHERE staff_id = ?");
				) {
			
			
			 //prepare a statement instead of creating to allow for parameter
			stmt.setInt(1, id);

			try (ResultSet rs = stmt.executeQuery();) {
				//query is still executed as with created statement 


				if (rs.next()) { //rs.next() returns true or false, similar to text I/O
					staff = new Staff();

					staff.setStaffId(rs.getInt("staff_id"));
					staff.setFirstName(rs.getString("first_Name"));
					staff.setLastName(rs.getString("last_Name"));
					staff.setAddressId(rs.getInt("address_id"));
					//staff.setFirstName(rs.getString("picture"));
					staff.setEmail(rs.getString("email"));
					staff.setStoreId(rs.getInt("store_id"));
					staff.setActive(rs.getInt("active"));
					staff.setUsername(rs.getString("username"));
					staff.setPassword(rs.getString("password"));
					staff.setLastUpdate(rs.getDate("last_update"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return staff;
	}

	public int insert(Staff staff) {
		
		registerDriver();
		
		int id = 0;
		
		try ( Connection connection = DriverManager.getConnection(URL,  USER, PWD);
			PreparedStatement pstmt = connection.prepareStatement("INSERT INTO staff (first_name, last_name, address_id, picture, "
					+ "email, store_id, active, username, password, last_update) " + 
					"VALUES (?, ?, ?, null, ?, ?, ?, ?, ?, CURDATE())", Statement.RETURN_GENERATED_KEYS);
				) {
			
			
			
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
				try (ResultSet resultSet = pstmt.getGeneratedKeys();) {
					if (resultSet.next()) {
						id = resultSet.getInt(1);
						staff.setStaffId(id);
					}
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
		
		registerDriver();
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement pstmt = connection.prepareStatement("UPDATE staff" + 
						" SET first_name = ?, last_name = ?, address_id = ?, email = ?, store_id = ?, active = ?, username = ?, "
						+ "password = ?, last_update =CURDATE() WHERE staff_id = ?", Statement.RETURN_GENERATED_KEYS);
				) {
			
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
		int rowAffected = 0;
		
		registerDriver();
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement pstmt = connection.prepareStatement("DELETE FROM staff WHERE staff_id = ?");) {


			
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