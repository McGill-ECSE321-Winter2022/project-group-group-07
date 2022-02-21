package ca.mcgill.ecse321.GroceryStoreBackend.model;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Store {

  //Store Attributes
  private int storeID;
  private String name;
  private String address;
  private String phoneNumber;
  private String email;
  private int employeeDiscountRate;
  private float pointToCashRatio;

  //Store Associations
  private GroceryStoreSoftwareSystem system;
  private Set<TimeSlot> holidays;

  public void setStoreID(int aStoreID)
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

  public void setEmployeeDiscountRate(int aEmployeeDiscountRate)
  {
    employeeDiscountRate = aEmployeeDiscountRate;
  }

  public void setPointToCashRatio(float aPointToCashRatio)
  {
    pointToCashRatio = aPointToCashRatio;
  }
  @Id
  public int getStoreID()
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

  public int getEmployeeDiscountRate()
  {
    return employeeDiscountRate;
  }

  public float getPointToCashRatio()
  {
    return pointToCashRatio;
  }

  @OneToOne
  public GroceryStoreSoftwareSystem getSystem()
  {
    return system;
  }
  public boolean setSystem(GroceryStoreSoftwareSystem system)
    {
        if (system == null)
        {
            return false;
        }
        this.system = system;
        return true;
    }

  @OneToMany
  public Set<TimeSlot> getHolidays()
  {
    return holidays;
  }
  public void setHolidays(Set<TimeSlot> holidayss)
  {
    this.holidays = holidayss;
  }

}