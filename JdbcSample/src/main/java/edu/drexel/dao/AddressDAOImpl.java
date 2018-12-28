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

import edu.drexel.domain.Address;

public class AddressDAOImpl implements AddressDAO {
	public static final String URL = "jdbc:mysql://localhost:3306/sakila";
	public static final String USER = "user1";
	public static final String PWD = "password";

	public List<Address> getAll() {
		List<Address> list = new ArrayList();
		
		registerDriver();

		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM address");
				) {
			//get a db connection
			
			
			while (rs.next()) {
				//extract and populate DB data, map to an address, and append to list
				Address address = new Address(); 
				address.setId(rs.getInt("address_id"));
				address.setAddress(rs.getString("address"));
				address.setAddress2(rs.getString("address2"));
				address.setDistrict(rs.getString("district"));
				address.setCityId(rs.getInt("city_id"));
				address.setPostalCode(rs.getString("postal_code"));
				address.setPhone(rs.getString("phone"));
				//address.setLocation(rs.getString("location"));
				address.setLastUpdate(rs.getDate("last_update"));
				
				list.add(address);	
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

	public Address findByID(int id) {
		
		registerDriver();
		
		Address address = null; 
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement stmt = connection.prepareStatement("SELECT * FROM address WHERE address_id = ?");
				) {

			
			//set parameter	
			stmt.setInt(1, id);
			
			//execute 
			try (ResultSet rs = stmt.executeQuery();) {

				if (rs.next()) {
					//extract and populate DB data, map to an address, and append to list
					address = new Address(); 
					address.setId(rs.getInt("address_id"));
					address.setAddress(rs.getString("address"));
					address.setAddress2(rs.getString("address2"));
					address.setDistrict(rs.getString("district"));
					address.setCityId(rs.getInt("city_id"));
					address.setPostalCode(rs.getString("postal_code"));
					address.setPhone(rs.getString("phone"));
					//address.setLocation(rs.getString("location"));
					address.setLastUpdate(rs.getDate("last_update"));
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return address;
	}

	public int insert(Address address) {
		registerDriver();
		
		int addressId = 0;
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement pstmt = connection.prepareStatement("INSERT INTO address (address, address2, district, city_id, "
						+ "postal_code, phone, location, last_update) " + 
						"VALUES (?, ?, ?, ?, ?, ?, point (43.7643, 80.2333), CURDATE())", Statement.RETURN_GENERATED_KEYS);
				) {
			
			//set parameters for prepared statement 
			pstmt.setString(1,  address.getAddress());
			pstmt.setString(2, address.getAddress2());
			pstmt.setString(3,  address.getDistrict());
			pstmt.setInt(4, address.getCityId());
			pstmt.setString(5,  address.getPostalCode());
			pstmt.setString(6, address.getPhone());
			
			//execute query
			int rowAffected = pstmt.executeUpdate();
			
			if (rowAffected == 1) {
				try (ResultSet resultSet = pstmt.getGeneratedKeys();) {
				if (resultSet.next()) {
					addressId = resultSet.getInt(1);
					address.setId(addressId);
				}
			} 
		}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return addressId;
	}

	public int update(Address address) {
		int rowAffected = 0;
		
		registerDriver();

		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement pstmt = connection.prepareStatement("UPDATE address " +
						" SET address=?, address2=?, district=?, city_id=?, postal_code=?, "
						+ "phone=?, location=point(43.7643, 80.2333), last_update = CURDATE() " + 
						"WHERE address_id = ?");
				) {

			
			
			//set parameters for prepared statement 
			pstmt.setString(1, address.getAddress());
			pstmt.setString(2, address.getAddress2());
			pstmt.setString(3, address.getDistrict());
			pstmt.setInt(4, address.getCityId());
			pstmt.setString(5, address.getPostalCode());
			pstmt.setString(6, address.getPhone());
			pstmt.setInt(7, address.getId());
			
			
			//execute query
			rowAffected = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return rowAffected;
	}

	public int delete(int id) {
		int rowAffected = 0;
		registerDriver();
		
		try (Connection connection = DriverManager.getConnection(URL,  USER, PWD);
				PreparedStatement pstmt = connection.prepareStatement("DELETE FROM address WHERE address_id = ?");
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
