package edu.drexel.model;

import java.util.Date;

/**
 * VO/DTO for address
 * @author AlanN
 *
 */
public class Address {

	private int id;
	private String address;
	private String address2;
	private String district;
	private int cityId;
	private String postalCode;
	private String phone;
	private String location;
	private Date lastUpdate;

	public Address(String address, String address2, String district, int cityId, String postalCode,
			String phone, String location, Date lastUpdate) {
		super();
		this.id = id;
		this.address = address;
		this.address2 = address2;
		this.district = district;
		this.cityId = cityId;
		this.postalCode = postalCode;
		this.phone = phone;
		this.location = location;
		this.lastUpdate = lastUpdate;
	}

	public Address() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", address=" + address + ", address2=" + address2 + ", district=" + district
				+ ", cityId=" + cityId + ", postalCode=" + postalCode + ", phone=" + phone + ", location=" + location
				+ ", lastUpdate=" + lastUpdate + "]";
	}
	
	
}

