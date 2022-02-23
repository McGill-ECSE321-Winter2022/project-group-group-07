package ca.mcgill.ecse321.grocerystore.model;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
public class Store {

  //Store Attributes
  private Integer storeID;
  private String name;
  private String address;
  private String phoneNumber;
  private String email;
  private Integer employeeDiscountRate;
  private Float pointToCashRatio;

  //Store Associations
  private Set<TimeSlot> holidays;

  public void setStoreID(Integer aStoreID)
  {
    storeID = aStoreID;
  }

  public void setName(String aName)
  {
    name = aName;
  }

  public void setAddress(String aAddress)
  {
    address = aAddress;
  }

  public void setPhoneNumber(String aPhoneNumber)
  {
    phoneNumber = aPhoneNumber;
  }

  public void setEmail(String aEmail)
  {
    email = aEmail;
  }

  public void setEmployeeDiscountRate(Integer aEmployeeDiscountRate)
  {
    employeeDiscountRate = aEmployeeDiscountRate;
  }

  public void setPointToCashRatio(Float aPointToCashRatio)
  {
    pointToCashRatio = aPointToCashRatio;
  }
  @Id
  public Integer getStoreID()
  {
    return storeID;
  }

  public String getName()
  {
    return name;
  }

  public String getAddress()
  {
    return address;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public String getEmail()
  {
    return email;
  }

  public Integer getEmployeeDiscountRate()
  {
    return employeeDiscountRate;
  }

  public Float getPointToCashRatio()
  {
    return pointToCashRatio;
  }

  @OneToMany(cascade={CascadeType.ALL})
  public Set<TimeSlot> getHolidays()
  {
    return holidays;
  }
  public void setHolidays(Set<TimeSlot> holidayss)
  {
    this.holidays = holidayss;
  }

}