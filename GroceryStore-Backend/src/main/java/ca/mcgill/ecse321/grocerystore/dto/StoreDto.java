package ca.mcgill.ecse321.grocerystore.dto;

import java.util.Collections;
import java.util.Set;

import ca.mcgill.ecse321.grocerystore.model.TimeSlot;

public class StoreDto {
    private Integer storeID;
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private Integer employeeDiscountRate;
	private Float pointToCashRatio;
    private Set<TimeSlot> holidays;
    
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
     */
    public StoreDto(Integer storeID, String name, String address, String phoneNumber, String email, Integer employeeDiscountRate, Float pointToCashRatio, Set<TimeSlot> holidays){
        this.storeID = storeID;
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
    public StoreDto(Integer storeID, String name, String address, String phoneNumber, String email, Integer employeeDiscountRate, Float pointToCashRatio){
        this.storeID = storeID;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.employeeDiscountRate = employeeDiscountRate;
        this.pointToCashRatio = pointToCashRatio;
        this.holidays = Collections.emptySet();
    }

    public Integer getID(){
        return this.storeID;
    }
    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String getEmail(){
        return this.email;
    }
    public Integer getEmployeeDiscountRate(){
        return this.employeeDiscountRate;
    }
    public Float getPointToCashRatio(){
        return this.pointToCashRatio;
    }
    public void setHolidays(Set<TimeSlot> holidays){
        this.holidays = holidays;
    }
    public Set<TimeSlot> getHolidays(){
        return this.holidays;
    }
}
