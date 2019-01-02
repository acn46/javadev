package edu.drexel.repo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.drexel.domain.Staff;

public class StaffRepoImpl extends AbstractRepoImpl<Staff> implements StaffRepo {

	public List<Staff> getAll() {
		return this.getAll("SELECT * FROM staff");
	}
	
	@Override
	protected Staff mapRow(ResultSet rs) throws SQLException {
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
		return staff;
	}

	public Staff findById(int id) {
		return this.findById("SELECT * FROM staff WHERE staff_id = ?", id);
	}
	
	@Override
	protected Staff mapRowEx(ResultSet rs) throws SQLException {
		Staff staff;
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
		return staff;
	}

	public int insert(Staff staff) {
		String sql = "INSERT INTO staff (first_name, last_name, address_id, picture, "
				+ "email, store_id, active, username, password, last_update) " + 
				"VALUES (?, ?, ?, null, ?, ?, ?, ?, ?, CURDATE())";
		return this.insert(sql, staff);
	}
	
	@Override
	protected void setInsertParams(Staff staff, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1,  staff.getFirstName());
		pstmt.setString(2, staff.getLastName());
		pstmt.setInt(3,  staff.getAddressId());
		pstmt.setString(4, staff.getEmail());
		pstmt.setInt(5,  staff.getStoreId());
		pstmt.setInt(6, staff.getActive());
		pstmt.setString(7, staff.getUsername());
		pstmt.setString(8, staff.getPassword());
	}
	
	@Override
	protected int handleInsertKey(Staff staff, int id, PreparedStatement pstmt) throws SQLException {
		try (ResultSet resultSet = pstmt.getGeneratedKeys();) {
			if (resultSet.next()) {
				id = resultSet.getInt(1);
				staff.setStaffId(id);
			}
		}
		return id;
	}
	
	public int update(Staff staff) {
		String sql = "UPDATE staff" + 
				" SET first_name = ?, last_name = ?, address_id = ?, email = ?, store_id = ?, active = ?, username = ?, "
				+ "password = ?, last_update =CURDATE() WHERE staff_id = ?";
		return this.update(sql, staff);
	}

	@Override
	protected void setUpdateParams(Staff staff, PreparedStatement pstmt) throws SQLException {
		setInsertParams(staff, pstmt);
		pstmt.setInt(9, staff.getStaffId());
	} 
	
		

	public int delete(int id) {
		String sql = "DELETE FROM staff WHERE staff_id = ?";
		return this.delete(sql, id);
	}

}