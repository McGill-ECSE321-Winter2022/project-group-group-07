package ca.mcgill.ecse321.GroceryStoreBackend.model;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
  private List<TimeSlot> holidays;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Store(int aStoreID, String aName, String aAddress, String aPhoneNumber, String aEmail, int aEmployeeDiscountRate, float aPointToCashRatio, GroceryStoreSoftwareSystem aSystem)
  {
    storeID = aStoreID;
    name = aName;
    address = aAddress;
    phoneNumber = aPhoneNumber;
    email = aEmail;
    employeeDiscountRate = aEmployeeDiscountRate;
    pointToCashRatio = aPointToCashRatio;
    if (aSystem == null || aSystem.getStore() != null)
    {
      throw new RuntimeException("Unable to create Store due because the given system is null or has already a Store assigned.");
    }
    system = aSystem;
    holidays = new ArrayList<TimeSlot>();
  }

  public Store(int aStoreID, String aName, String aAddress, String aPhoneNumber, String aEmail, int aEmployeeDiscountRate, float aPointToCashRatio, int aSystemIDForSystem, Owner aOwnerForGsss)
  {
    storeID = aStoreID;
    name = aName;
    address = aAddress;
    phoneNumber = aPhoneNumber;
    email = aEmail;
    employeeDiscountRate = aEmployeeDiscountRate;
    pointToCashRatio = aPointToCashRatio;
    system = new GroceryStoreSoftwareSystem(aSystemIDForSystem, this, aOwnerForGsss);
    holidays = new ArrayList<TimeSlot>();
  }

  //------------------------
  // INTERFACE
  //------------------------

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
  /* Code from template association_GetOne */
  public GroceryStoreSoftwareSystem getGsss()
  {
    return system;
  }
  /* Code from template association_GetMany */
  public TimeSlot getHoliday(int index)
  {
    TimeSlot aHoliday = holidays.get(index);
    return aHoliday;
  }
  @OneToMany
  public List<TimeSlot> getHolidays()
  {
    List<TimeSlot> newHolidays = Collections.unmodifiableList(holidays);
    return newHolidays;
  }

  public int numberOfHolidays()
  {
    return holidays.size();
  }

  public boolean hasHolidays()
  {
    return (holidays.size() > 0);
  }

  public int indexOfHoliday(TimeSlot aHoliday)
  {
    int index = holidays.indexOf(aHoliday);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHolidays()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addHoliday(TimeSlot aHoliday)
  {
    if (holidays.contains(aHoliday)) { return false; }
    holidays.add(aHoliday);
    return true;
  }

  public boolean removeHoliday(TimeSlot aHoliday)
  {
    if (holidays.contains(aHoliday))
    {
      holidays.remove(aHoliday);
      return true;
    }
    return false;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHolidayAt(TimeSlot aHoliday, int index)
  {  
    boolean wasAdded = false;
    if(addHoliday(aHoliday))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHolidays()) { index = numberOfHolidays() - 1; }
      holidays.remove(aHoliday);
      holidays.add(index, aHoliday);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHolidayAt(TimeSlot aHoliday, int index)
  {
    boolean wasAdded = false;
    if(holidays.contains(aHoliday))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHolidays()) { index = numberOfHolidays() - 1; }
      holidays.remove(aHoliday);
      holidays.add(index, aHoliday);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHolidayAt(aHoliday, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    GroceryStoreSoftwareSystem existingGsss = system;
    system = null;
    if (existingGsss != null)
    {
      existingGsss.delete();
    }
    holidays.clear();
  }


  public String toString()
  {
    return super.toString() + "["+
            "storeID" + ":" + getStoreID()+ "," +
            "name" + ":" + getName()+ "," +
            "address" + ":" + getAddress()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "email" + ":" + getEmail()+ "," +
            "employeeDiscountRate" + ":" + getEmployeeDiscountRate()+ "," +
            "pointToCashRatio" + ":" + getPointToCashRatio()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gsss = "+(getGsss()!=null?Integer.toHexString(System.identityHashCode(getGsss())):"null");
  }
}