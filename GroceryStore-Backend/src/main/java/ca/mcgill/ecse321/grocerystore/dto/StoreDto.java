package ca.mcgill.ecse321.grocerystore.dto;

import java.util.Collections;
import java.util.List;

public class StoreDto {

	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private Integer employeeDiscountRate;
	private Float pointToCashRatio;
	private List<TimeSlotDto> holidays;
	private List<BusinessHourDto> businessHours;

	/**
	 * 
	 * @param storeID
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param email
	 * @param employeeDiscountRate
	 * @param pointToCashRatio
	 * @param holidays
	 * @param businessHours
	 */
	public StoreDto(String name, String address, String phoneNumber, String email, Integer employeeDiscountRate,
			Float pointToCashRatio, List<TimeSlotDto> holidays, List<BusinessHourDto> businessHours) {

		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.employeeDiscountRate = employeeDiscountRate;
		this.pointToCashRatio = pointToCashRatio;
		this.holidays = holidays;
	}

	/**
	 * 
	 * @param storeID
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param email
	 * @param employeeDiscountRate
	 * @param pointToCashRatio
	 */
	@SuppressWarnings("unchecked")
	public StoreDto(String name, String address, String phoneNumber, String email, Integer employeeDiscountRate,
			Float pointToCashRatio, List<BusinessHourDto> businessHours) {

		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.employeeDiscountRate = employeeDiscountRate;
		this.pointToCashRatio = pointToCashRatio;
		this.holidays = Collections.EMPTY_LIST;
		this.businessHours = businessHours;
	}

	/**
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param email
	 * @param employeeDiscountRate
	 * @param pointToCashRatio
	 */
	@SuppressWarnings("unchecked")
	public StoreDto(String name, String address, String phoneNumber, String email, Integer employeeDiscountRate,
			Float pointToCashRatio) {
		this(name, address, phoneNumber, email, employeeDiscountRate, pointToCashRatio, Collections.EMPTY_LIST,
				Collections.EMPTY_LIST);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the employeeDiscountRate
	 */
	public Integer getEmployeeDiscountRate() {
		return employeeDiscountRate;
	}

	/**
	 * @return the pointToCashRatio
	 */
	public Float getPointToCashRatio() {
		return pointToCashRatio;
	}

	/**
	 * @return the holidays
	 */
	public List<TimeSlotDto> getHolidays() {
		return holidays;
	}

	/**
	 * @return the businessHours
	 */
	public List<BusinessHourDto> getBusinessHours() {
		return businessHours;
	}
}
