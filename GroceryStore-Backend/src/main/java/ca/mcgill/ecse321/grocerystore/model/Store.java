package ca.mcgill.ecse321.grocerystore.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
public class Store {

	// attribute fields
	private Long storeID;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private Integer employeeDiscountRate;
	private Float pointToCashRatio;
	// association fields
	private Set<TimeSlot> holidays;

	// getters and setters

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getStoreID() {
		return storeID;
	}

	public void setStoreID(Long aStoreID) {
		storeID = aStoreID;
	}

	public String getName() {
		return name;
	}

	public void setName(String aName) {
		name = aName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String aAddress) {
		address = aAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String aPhoneNumber) {
		phoneNumber = aPhoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String aEmail) {
		email = aEmail;
	}

	public Integer getEmployeeDiscountRate() {
		return employeeDiscountRate;
	}

	public void setEmployeeDiscountRate(Integer aEmployeeDiscountRate) {
		employeeDiscountRate = aEmployeeDiscountRate;
	}

	public Float getPointToCashRatio() {
		return pointToCashRatio;
	}

	public void setPointToCashRatio(Float aPointToCashRatio) {
		pointToCashRatio = aPointToCashRatio;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<TimeSlot> getHolidays() {
		return holidays;
	}

	public void setHolidays(Set<TimeSlot> holidayss) {
		this.holidays = holidayss;
	}

}