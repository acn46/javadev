package edu.drexel.repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.drexel.domain.Address;

public class AddressRepoImpl extends AbstractRepoImpl<Address> implements AddressRepo {

	public List<Address> getAll() {
		return getAll("SELECT * FROM address", (rs) -> mapRow(rs) );
	}
	
	private Address mapRow(ResultSet rs) throws SQLException {
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
		return address;
	}

	public Address findByID(int id) {
		return findById("SELECT * FROM address WHERE address_id = ?", id, (rs) -> mapRowEx(rs) );
		
	}
	
	private Address mapRowEx(ResultSet rs) throws SQLException {
		Address address;
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
		return address;
	}

	public int insert(Address address) {
		String sql ="INSERT INTO address (address, address2, district, city_id, "
						+ "postal_code, phone, location, last_update) " + 
						"VALUES (?, ?, ?, ?, ?, ?, point (43.7643, 80.2333), CURDATE())";
		return insert(sql, address, (address1, pstmt) -> setInsertParams(address1, pstmt) );
	}

	@Override
	protected int handleInsertKey(Address address, int addressId, PreparedStatement pstmt) throws SQLException {
		try (ResultSet resultSet = pstmt.getGeneratedKeys();) {
		if (resultSet.next()) {
			addressId = resultSet.getInt(1);
			address.setId(addressId);
		}
}
		return addressId;
	}
	
	private void setInsertParams(Address address, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1,  address.getAddress());
		pstmt.setString(2, address.getAddress2());
		pstmt.setString(3,  address.getDistrict());
		pstmt.setInt(4, address.getCityId());
		pstmt.setString(5,  address.getPostalCode());
		pstmt.setString(6, address.getPhone());
	}

	public int update(Address address) {
		String sql = "UPDATE address " +
						" SET address=?, address2=?, district=?, city_id=?, postal_code=?, "
						+ "phone=?, location=point(43.7643, 80.2333), last_update = CURDATE() " + 
						"WHERE address_id = ?";
		return update(sql, address, (address1, pstmt) -> setUpdateParams(address1, pstmt) );
	}
	
	private void setUpdateParams(Address address, PreparedStatement pstmt) throws SQLException {
		setInsertParams(address, pstmt);
		pstmt.setInt(7, address.getId());
	}

	public int delete(int id) {
		return delete("DELETE FROM address WHERE address_id = ?", id);
	}
}
	
