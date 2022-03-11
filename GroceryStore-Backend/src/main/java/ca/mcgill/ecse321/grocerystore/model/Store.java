package ca.mcgill.ecse321.grocerystore.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
public class Store {

	// attribute fields
	private Integer storeID;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private Integer employeeDiscountRate;
	private Float pointToCashRatio;
	// association fields
	private Set<TimeSlot> holidays;

	// getters and setters
	public void setStoreID(Integer aStoreID) {
		storeID = aStoreID;
	}

	@Id
	public Integer getStoreID() {
		return storeID;
	}

	public void setName(String aName) {
		name = aName;
	}

	public String getName() {
		return name;
	}

	public void setAddress(String aAddress) {
		address = aAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setPhoneNumber(String aPhoneNumber) {
		phoneNumber = aPhoneNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setEmail(String aEmail) {
		email = aEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmployeeDiscountRate(Integer aEmployeeDiscountRate) {
		employeeDiscountRate = aEmployeeDiscountRate;
	}

	public Integer getEmployeeDiscountRate() {
		return employeeDiscountRate;
	}

	public void setPointToCashRatio(Float aPointToCashRatio) {
		pointToCashRatio = aPointToCashRatio;
	}

	public Float getPointToCashRatio() {
		return pointToCashRatio;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<TimeSlot> getHolidays() {
		return holidays;
	}

	public void setHolidays(Set<TimeSlot> holidayss) {
		this.holidays = holidayss;
	}

}