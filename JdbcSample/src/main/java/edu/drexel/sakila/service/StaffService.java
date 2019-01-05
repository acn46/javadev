package edu.drexel.sakila.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import edu.drexel.domain.Staff;
import edu.drexel.repo.StaffRepo;
import edu.drexel.repo.StaffRepoImpl;

@Path("/staffs")
public class StaffService {
	
	@GET
	public List<Staff> getAll() {
		System.out.println("\nin getAll");
		StaffRepo dao = new StaffRepoImpl();
		List<Staff> list = dao.getAll();
		return list;

	}
	
	@GET
	@Path("/{id}")
	public Staff findByID(@PathParam("id") int id) {
		System.out.println("\nin findByID");
		StaffRepo dao = new StaffRepoImpl();
		Staff staff = dao.findById(id);
		return staff;
	}
	
	@PUT
	public int insert(Staff staff) {
		System.out.println("\nin Insert");
		StaffRepo dao = new StaffRepoImpl();
		//Staff staff = new Staff("John", "Doe", 1, "John@compmail.com", null, 2, 1, "JDoe", "dwe23321", null);
		int staffId = dao.insert(staff);
		return staffId;
	}
	
	@POST
	public int update(Staff staff) {
		System.out.println("\nin update");
		StaffRepo dao = new StaffRepoImpl();
		//Staff staff = new Staff("John1", "Doe", 1, "John@compmail.com", null, 2, 1, "JDoe", "dwe23321", null);
		//staff.setStaffId(10);
		int rowAffected = dao.update(staff);
		return rowAffected;
	}
	
	@DELETE
	@Path("/{id}")
	public int delete(@PathParam("id") int id) {
		System.out.println("\nin delete");
		StaffRepo dao = new StaffRepoImpl();
		int rowAffected = dao.delete(id);
		return rowAffected;
	}

}
