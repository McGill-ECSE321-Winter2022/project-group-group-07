package ca.mcgill.ecse321.GroceryStoreBackend.model;
import java.util.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import java.sql.Time;
import java.sql.Date;

@Entity
public class GroceryStoreSoftwareSystem
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
  public enum OrderType { InStore, Delivery, PickUp }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //GroceryStoreSoftwareSystem Attributes
  private int gsssID;

  //GroceryStoreSoftwareSystem Associations
  private Store store;
  private List<BusinessHour> hours;
  private List<Schedule> employeeSchedules;
  private List<Terminal> terminals;
  private List<Report> salesReports;
  private Owner owner;
  private List<Cashier> cashiers;
  private List<DeliveryPerson> deliverypersons;
  private List<Clerk> clerks;
  private List<Customer> customers;
  private List<Item> items;
  private List<Order> orders;
  private List<TimeSlot> timeSlots;
  private List<Cart> carts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public GroceryStoreSoftwareSystem(int aGsssID, Store aStore, Owner aOwner)
  {
    gsssID = aGsssID;
    if (aStore == null || aStore.getGsss() != null)
    {
      throw new RuntimeException("Unable to create GroceryStoreSoftwareSystem due to aStore. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    store = aStore;
    hours = new ArrayList<BusinessHour>();
    employeeSchedules = new ArrayList<Schedule>();
    terminals = new ArrayList<Terminal>();
    salesReports = new ArrayList<Report>();
    if (aOwner == null || aOwner.getGsss() != null)
    {
      throw new RuntimeException("Unable to create GroceryStoreSoftwareSystem due to aOwner. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    owner = aOwner;
    cashiers = new ArrayList<Cashier>();
    deliverypersons = new ArrayList<DeliveryPerson>();
    clerks = new ArrayList<Clerk>();
    customers = new ArrayList<Customer>();
    items = new ArrayList<Item>();
    orders = new ArrayList<Order>();
    timeSlots = new ArrayList<TimeSlot>();
    carts = new ArrayList<Cart>();
  }

  public GroceryStoreSoftwareSystem(int aGsssID, int aStoreIDForStore, String aNameForStore, String aAddressForStore, String aPhoneNumberForStore, String aEmailForStore, int aEmployeeDiscountRateForStore, float aPointToCashRatioForStore, Date aEmploymentDateForOwner)
  {
    gsssID = aGsssID;
    store = new Store(aStoreIDForStore, aNameForStore, aAddressForStore, aPhoneNumberForStore, aEmailForStore, aEmployeeDiscountRateForStore, aPointToCashRatioForStore, this);
    hours = new ArrayList<BusinessHour>();
    employeeSchedules = new ArrayList<Schedule>();
    terminals = new ArrayList<Terminal>();
    salesReports = new ArrayList<Report>();
    owner = new Owner(aEmploymentDateForOwner, this);
    cashiers = new ArrayList<Cashier>();
    deliverypersons = new ArrayList<DeliveryPerson>();
    clerks = new ArrayList<Clerk>();
    customers = new ArrayList<Customer>();
    items = new ArrayList<Item>();
    orders = new ArrayList<Order>();
    timeSlots = new ArrayList<TimeSlot>();
    carts = new ArrayList<Cart>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void setGsssID(int aGsssID)
  {
    gsssID = aGsssID;
  }
  @Id
  public int getGsssID()
  {
    return gsssID;
  }
  /* Code from template association_GetOne */
  @OneToOne
  public Store getStore()
  {
    return store;
  }
  /* Code from template association_GetMany */
  public BusinessHour getHour(int index)
  {
    BusinessHour aHour = hours.get(index);
    return aHour;
  }
  @OneToMany
  public List<BusinessHour> getHours()
  {
    List<BusinessHour> newHours = Collections.unmodifiableList(hours);
    return newHours;
  }

  public int numberOfHours()
  {
    return hours.size();
  }

  public boolean hasHours()
  {
    return (hours.size() > 0);
  }

  public int indexOfHour(BusinessHour aHour)
  {
    int index = hours.indexOf(aHour);
    return index;
  }
  /* Code from template association_GetMany */
  public Schedule getEmployeeSchedule(int index)
  {
    Schedule aEmployeeSchedule = employeeSchedules.get(index);
    return aEmployeeSchedule;
  }
  @OneToMany
  public List<Schedule> getEmployeeSchedules()
  {
    List<Schedule> newEmployeeSchedules = Collections.unmodifiableList(employeeSchedules);
    return newEmployeeSchedules;
  }

  public int numberOfEmployeeSchedules()
  {
    return employeeSchedules.size();
  }

  public boolean hasEmployeeSchedules()
  {
    return (employeeSchedules.size() > 0);
  }

  public int indexOfEmployeeSchedule(Schedule aEmployeeSchedule)
  {
    return employeeSchedules.indexOf(aEmployeeSchedule);
  }
  /* Code from template association_GetMany */
  public Terminal getTerminal(int index)
  {
    Terminal aTerminal = terminals.get(index);
    return aTerminal;
  }
  @OneToMany
  public List<Terminal> getTerminals()
  {
    List<Terminal> newTerminals = Collections.unmodifiableList(terminals);
    return newTerminals;
  }

  public int numberOfTerminals()
  {
    return terminals.size();
  }

  public boolean hasTerminals()
  {
    return (terminals.size() > 0);
  }

  public int indexOfTerminal(Terminal aTerminal)
  {
    return terminals.indexOf(aTerminal);
  }
  /* Code from template association_GetMany */
  public Report getSalesReport(int index)
  {
    Report aSalesReport = salesReports.get(index);
    return aSalesReport;
  }
  @OneToMany
  public List<Report> getSalesReports()
  {
    List<Report> newSalesReports = Collections.unmodifiableList(salesReports);
    return newSalesReports;
  }

  public int numberOfSalesReports()
  {
    return salesReports.size();
  }

  public boolean hasSalesReports()
  {
    return (salesReports.size() > 0);
  }

  public int indexOfSalesReport(Report aSalesReport)
  {
    return salesReports.indexOf(aSalesReport);
  }
  /* Code from template association_GetOne */
  @OneToOne
  public Owner getOwner()
  {
    return owner;
  }
  /* Code from template association_GetMany */
  public Cashier getCashier(int index)
  {
    Cashier aCashier = cashiers.get(index);
    return aCashier;
  }
  @OneToMany
  public List<Cashier> getCashiers()
  {
    List<Cashier> newCashiers = Collections.unmodifiableList(cashiers);
    return newCashiers;
  }

  public int numberOfCashiers()
  {
    return cashiers.size();
  }

  public boolean hasCashiers()
  {
    return cashiers.size() > 0;
  }

  public int indexOfCashier(Cashier aCashier)
  {
    return cashiers.indexOf(aCashier);
  }
  /* Code from template association_GetMany */
  public DeliveryPerson getDeliveryperson(int index)
  {
    DeliveryPerson aDeliveryperson = deliverypersons.get(index);
    return aDeliveryperson;
  }
  @OneToMany
  public List<DeliveryPerson> getDeliverypersons()
  {
    List<DeliveryPerson> newDeliverypersons = Collections.unmodifiableList(deliverypersons);
    return newDeliverypersons;
  }

  public int numberOfDeliverypersons()
  {
    return deliverypersons.size();
  }

  public boolean hasDeliverypersons()
  {
    return (deliverypersons.size() > 0);
  }

  public int indexOfDeliveryperson(DeliveryPerson aDeliveryperson)
  {
    return deliverypersons.indexOf(aDeliveryperson);
  }
  /* Code from template association_GetMany */
  public Clerk getClerk(int index)
  {
    Clerk aClerk = clerks.get(index);
    return aClerk;
  }
  @OneToMany
  public List<Clerk> getClerks()
  {
    List<Clerk> newClerks = Collections.unmodifiableList(clerks);
    return newClerks;
  }

  public int numberOfClerks()
  {
    return clerks.size();
  }

  public boolean hasClerks()
  {
    return (clerks.size() > 0);
  }

  public int indexOfClerk(Clerk aClerk)
  {
    return clerks.indexOf(aClerk);
  }
  /* Code from template association_GetMany */
  public Customer getCustomer(int index)
  {
    Customer aCustomer = customers.get(index);
    return aCustomer;
  }
  @OneToMany
  public List<Customer> getCustomers()
  {
    List<Customer> newCustomers = Collections.unmodifiableList(customers);
    return newCustomers;
  }

  public int numberOfCustomers()
  {
    return customers.size();
  }

  public boolean hasCustomers()
  {
    return (customers.size() > 0);
  }

  public int indexOfCustomer(Customer aCustomer)
  {
    return customers.indexOf(aCustomer);
  }
  /* Code from template association_GetMany */
  public Item getItem(int index)
  {
    Item aItem = items.get(index);
    return aItem;
  }
  @OneToMany
  public List<Item> getItems()
  {
    List<Item> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    return items.size();
  }

  public boolean hasItems()
  {
    return (items.size() > 0);
  }

  public int indexOfItem(Item aItem)
  {
    return items.indexOf(aItem);
  }
  /* Code from template association_GetMany */
  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }
  @OneToMany
  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    return orders.size();
  }

  public boolean hasOrders()
  {
    return (orders.size() > 0);
  }

  public int indexOfOrder(Order aOrder)
  {
    return orders.indexOf(aOrder);
  }
  /* Code from template association_GetMany */
  public TimeSlot getTimeSlot(int index)
  {
    TimeSlot aTimeSlot = timeSlots.get(index);
    return aTimeSlot;
  }
  @OneToMany
  public List<TimeSlot> getTimeSlots()
  {
    List<TimeSlot> newTimeSlots = Collections.unmodifiableList(timeSlots);
    return newTimeSlots;
  }

  public int numberOfTimeSlots()
  {
    return timeSlots.size();
  }

  public boolean hasTimeSlots()
  {
    return (timeSlots.size() > 0);
  }

  public int indexOfTimeSlot(TimeSlot aTimeSlot)
  {
    return timeSlots.indexOf(aTimeSlot);
  }
  /* Code from template association_GetMany */
  public Cart getCart(int index)
  {
    Cart aCart = carts.get(index);
    return aCart;
  }
  @OneToMany
  public List<Cart> getCarts()
  {
    List<Cart> newCarts = Collections.unmodifiableList(carts);
    return newCarts;
  }

  public int numberOfCarts()
  {
    return carts.size();
  }

  public boolean hasCarts()
  {
    return (carts.size() > 0);
  }

  public int indexOfCart(Cart aCart)
  {
    int index = carts.indexOf(aCart);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfHours()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public BusinessHour addHour(int aBusinessHourId, BusinessHour.DayOfWeek aDayOfWeek, Time aStartTime, Time aEndTime, Store aStore)
  {
    return new BusinessHour(aBusinessHourId, aDayOfWeek, aStartTime, aEndTime, aStore, this);
  }

  public boolean addHour(BusinessHour aHour)
  {
    if (hours.contains(aHour)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aHour.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aHour.setGsss(this);
    }
    else
    {
      hours.add(aHour);
    }
    return true;
  }

  public boolean removeHour(BusinessHour aHour)
  {
    boolean wasRemoved = false;
    //Unable to remove aHour, as it must always have a gsss
    if (!this.equals(aHour.getGsss()))
    {
      hours.remove(aHour);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addHourAt(BusinessHour aHour, int index)
  {  
    boolean wasAdded = false;
    if(addHour(aHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHours()) { index = numberOfHours() - 1; }
      hours.remove(aHour);
      hours.add(index, aHour);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveHourAt(BusinessHour aHour, int index)
  {
    boolean wasAdded = false;
    if(hours.contains(aHour))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfHours()) { index = numberOfHours() - 1; }
      hours.remove(aHour);
      hours.add(index, aHour);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addHourAt(aHour, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEmployeeSchedules()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Schedule addEmployeeSchedule(int aScheduleID, Employee aEmployee)
  {
    return new Schedule(aScheduleID, aEmployee, this);
  }

  public boolean addEmployeeSchedule(Schedule aEmployeeSchedule)
  {
    boolean wasAdded = false;
    if (employeeSchedules.contains(aEmployeeSchedule)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aEmployeeSchedule.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aEmployeeSchedule.setGsss(this);
    }
    else
    {
      employeeSchedules.add(aEmployeeSchedule);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployeeSchedule(Schedule aEmployeeSchedule)
  {
    boolean wasRemoved = false;
    //Unable to remove aEmployeeSchedule, as it must always have a gsss
    if (!this.equals(aEmployeeSchedule.getGsss()))
    {
      employeeSchedules.remove(aEmployeeSchedule);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEmployeeScheduleAt(Schedule aEmployeeSchedule, int index)
  {  
    boolean wasAdded = false;
    if(addEmployeeSchedule(aEmployeeSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployeeSchedules()) { index = numberOfEmployeeSchedules() - 1; }
      employeeSchedules.remove(aEmployeeSchedule);
      employeeSchedules.add(index, aEmployeeSchedule);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeScheduleAt(Schedule aEmployeeSchedule, int index)
  {
    boolean wasAdded = false;
    if(employeeSchedules.contains(aEmployeeSchedule))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployeeSchedules()) { index = numberOfEmployeeSchedules() - 1; }
      employeeSchedules.remove(aEmployeeSchedule);
      employeeSchedules.add(index, aEmployeeSchedule);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeScheduleAt(aEmployeeSchedule, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTerminals()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Terminal addTerminal(int aTerminalID)
  {
    return new Terminal(aTerminalID, this);
  }

  public boolean addTerminal(Terminal aTerminal)
  {
    if (terminals.contains(aTerminal)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aTerminal.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aTerminal.setGsss(this);
    }
    else
    {
      terminals.add(aTerminal);
    }
    return true;
  }

  public boolean removeTerminal(Terminal aTerminal)
  {
    boolean wasRemoved = false;
    //Unable to remove aTerminal, as it must always have a gsss
    if (!this.equals(aTerminal.getGsss()))
    {
      terminals.remove(aTerminal);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTerminalAt(Terminal aTerminal, int index)
  {  
    boolean wasAdded = false;
    if(addTerminal(aTerminal))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTerminals()) { index = numberOfTerminals() - 1; }
      terminals.remove(aTerminal);
      terminals.add(index, aTerminal);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTerminalAt(Terminal aTerminal, int index)
  {
    boolean wasAdded = false;
    if(terminals.contains(aTerminal))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTerminals()) { index = numberOfTerminals() - 1; }
      terminals.remove(aTerminal);
      terminals.add(index, aTerminal);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTerminalAt(aTerminal, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSalesReports()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Report addSalesReport(int aReportID, Date aStartDate, Date aEndDate, float aTotalValue)
  {
    return new Report(aReportID, aStartDate, aEndDate, aTotalValue, this);
  }

  public boolean addSalesReport(Report aSalesReport)
  {
    boolean wasAdded = false;
    if (salesReports.contains(aSalesReport)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aSalesReport.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aSalesReport.setGsss(this);
    }
    else
    {
      salesReports.add(aSalesReport);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSalesReport(Report aSalesReport)
  {
    boolean wasRemoved = false;
    //Unable to remove aSalesReport, as it must always have a gsss
    if (!this.equals(aSalesReport.getGsss()))
    {
      salesReports.remove(aSalesReport);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSalesReportAt(Report aSalesReport, int index)
  {  
    boolean wasAdded = false;
    if(addSalesReport(aSalesReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSalesReports()) { index = numberOfSalesReports() - 1; }
      salesReports.remove(aSalesReport);
      salesReports.add(index, aSalesReport);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSalesReportAt(Report aSalesReport, int index)
  {
    boolean wasAdded = false;
    if(salesReports.contains(aSalesReport))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSalesReports()) { index = numberOfSalesReports() - 1; }
      salesReports.remove(aSalesReport);
      salesReports.add(index, aSalesReport);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSalesReportAt(aSalesReport, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCashiers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Cashier addCashier(Date aEmploymentDate)
  {
    return new Cashier(aEmploymentDate, this);
  }

  public boolean addCashier(Cashier aCashier)
  {
    boolean wasAdded = false;
    if (cashiers.contains(aCashier)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aCashier.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aCashier.setGsss(this);
    }
    else
    {
      cashiers.add(aCashier);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCashier(Cashier aCashier)
  {
    boolean wasRemoved = false;
    //Unable to remove aCashier, as it must always have a gsss
    if (!this.equals(aCashier.getGsss()))
    {
      cashiers.remove(aCashier);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCashierAt(Cashier aCashier, int index)
  {  
    boolean wasAdded = false;
    if(addCashier(aCashier))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCashiers()) { index = numberOfCashiers() - 1; }
      cashiers.remove(aCashier);
      cashiers.add(index, aCashier);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCashierAt(Cashier aCashier, int index)
  {
    boolean wasAdded = false;
    if(cashiers.contains(aCashier))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCashiers()) { index = numberOfCashiers() - 1; }
      cashiers.remove(aCashier);
      cashiers.add(index, aCashier);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCashierAt(aCashier, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfDeliverypersons()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public DeliveryPerson addDeliveryperson(Date aEmploymentDate)
  {
    return new DeliveryPerson(aEmploymentDate, this);
  }

  public boolean addDeliveryperson(DeliveryPerson aDeliveryperson)
  {
    boolean wasAdded = false;
    if (deliverypersons.contains(aDeliveryperson)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aDeliveryperson.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aDeliveryperson.setGsss(this);
    }
    else
    {
      deliverypersons.add(aDeliveryperson);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeDeliveryperson(DeliveryPerson aDeliveryperson)
  {
    boolean wasRemoved = false;
    //Unable to remove aDeliveryperson, as it must always have a gsss
    if (!this.equals(aDeliveryperson.getGsss()))
    {
      deliverypersons.remove(aDeliveryperson);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addDeliverypersonAt(DeliveryPerson aDeliveryperson, int index)
  {  
    boolean wasAdded = false;
    if(addDeliveryperson(aDeliveryperson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDeliverypersons()) { index = numberOfDeliverypersons() - 1; }
      deliverypersons.remove(aDeliveryperson);
      deliverypersons.add(index, aDeliveryperson);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveDeliverypersonAt(DeliveryPerson aDeliveryperson, int index)
  {
    boolean wasAdded = false;
    if(deliverypersons.contains(aDeliveryperson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfDeliverypersons()) { index = numberOfDeliverypersons() - 1; }
      deliverypersons.remove(aDeliveryperson);
      deliverypersons.add(index, aDeliveryperson);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addDeliverypersonAt(aDeliveryperson, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfClerks()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Clerk addClerk(Date aEmploymentDate)
  {
    return new Clerk(aEmploymentDate, this);
  }

  public boolean addClerk(Clerk aClerk)
  {
    boolean wasAdded = false;
    if (clerks.contains(aClerk)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aClerk.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aClerk.setGsss(this);
    }
    else
    {
      clerks.add(aClerk);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeClerk(Clerk aClerk)
  {
    boolean wasRemoved = false;
    //Unable to remove aClerk, as it must always have a gsss
    if (!this.equals(aClerk.getGsss()))
    {
      clerks.remove(aClerk);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addClerkAt(Clerk aClerk, int index)
  {  
    boolean wasAdded = false;
    if(addClerk(aClerk))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClerks()) { index = numberOfClerks() - 1; }
      clerks.remove(aClerk);
      clerks.add(index, aClerk);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveClerkAt(Clerk aClerk, int index)
  {
    boolean wasAdded = false;
    if(clerks.contains(aClerk))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfClerks()) { index = numberOfClerks() - 1; }
      clerks.remove(aClerk);
      clerks.add(index, aClerk);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addClerkAt(aClerk, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCustomers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Customer addCustomer()
  {
    return new Customer(this);
  }

  public boolean addCustomer(Customer aCustomer)
  {
    boolean wasAdded = false;
    if (customers.contains(aCustomer)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aCustomer.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aCustomer.setGsss(this);
    }
    else
    {
      customers.add(aCustomer);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCustomer(Customer aCustomer)
  {
    boolean wasRemoved = false;
    //Unable to remove aCustomer, as it must always have a gsss
    if (!this.equals(aCustomer.getGsss()))
    {
      customers.remove(aCustomer);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCustomerAt(Customer aCustomer, int index)
  {  
    boolean wasAdded = false;
    if(addCustomer(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCustomerAt(Customer aCustomer, int index)
  {
    boolean wasAdded = false;
    if(customers.contains(aCustomer))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomers()) { index = numberOfCustomers() - 1; }
      customers.remove(aCustomer);
      customers.add(index, aCustomer);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCustomerAt(aCustomer, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aItem.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aItem.setGsss(this);
    }
    else
    {
      items.add(aItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aItem, as it must always have a gsss
    if (!this.equals(aItem.getGsss()))
    {
      items.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(Item aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrders()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Order addOrder(int aOrderID, OrderType aOrderType, float aTotalValue, Date aDate, Item... allItems)
  {
    return new Order(aOrderID, aOrderType, aTotalValue, aDate, this, allItems);
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aOrder.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aOrder.setGsss(this);
    }
    else
    {
      orders.add(aOrder);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrder, as it must always have a gsss
    if (!this.equals(aOrder.getGsss()))
    {
      orders.remove(aOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfTimeSlots()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public TimeSlot addTimeSlot(int aTimeSlotID, Date aStartDate, Date aEndDate, Time aStartTime, Time aEndTime)
  {
    return new TimeSlot(aTimeSlotID, aStartDate, aEndDate, aStartTime, aEndTime, this);
  }

  public boolean addTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasAdded = false;
    if (timeSlots.contains(aTimeSlot)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aTimeSlot.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aTimeSlot.setGsss(this);
    }
    else
    {
      timeSlots.add(aTimeSlot);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTimeSlot(TimeSlot aTimeSlot)
  {
    boolean wasRemoved = false;
    //Unable to remove aTimeSlot, as it must always have a gsss
    if (!this.equals(aTimeSlot.getGsss()))
    {
      timeSlots.remove(aTimeSlot);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addTimeSlotAt(TimeSlot aTimeSlot, int index)
  {  
    boolean wasAdded = false;
    if(addTimeSlot(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTimeSlotAt(TimeSlot aTimeSlot, int index)
  {
    boolean wasAdded = false;
    if(timeSlots.contains(aTimeSlot))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTimeSlots()) { index = numberOfTimeSlots() - 1; }
      timeSlots.remove(aTimeSlot);
      timeSlots.add(index, aTimeSlot);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTimeSlotAt(aTimeSlot, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCarts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Cart addCart(int aCartID, OrderType aOrderType, float aTotalValue, int aNumOfItems, User aUser, TimeSlot aTimeSlot)
  {
    return new Cart(aCartID, aOrderType, aTotalValue, aNumOfItems, aUser, this, aTimeSlot);
  }

  public boolean addCart(Cart aCart)
  {
    boolean wasAdded = false;
    if (carts.contains(aCart)) { return false; }
    GroceryStoreSoftwareSystem existingGsss = aCart.getGsss();
    boolean isNewGsss = existingGsss != null && !this.equals(existingGsss);
    if (isNewGsss)
    {
      aCart.setGsss(this);
    }
    else
    {
      carts.add(aCart);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCart(Cart aCart)
  {
    boolean wasRemoved = false;
    //Unable to remove aCart, as it must always have a gsss
    if (!this.equals(aCart.getGsss()))
    {
      carts.remove(aCart);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCartAt(Cart aCart, int index)
  {  
    boolean wasAdded = false;
    if(addCart(aCart))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCarts()) { index = numberOfCarts() - 1; }
      carts.remove(aCart);
      carts.add(index, aCart);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCartAt(Cart aCart, int index)
  {
    boolean wasAdded = false;
    if(carts.contains(aCart))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCarts()) { index = numberOfCarts() - 1; }
      carts.remove(aCart);
      carts.add(index, aCart);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCartAt(aCart, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Store existingStore = store;
    store = null;
    if (existingStore != null)
    {
      existingStore.delete();
    }
    while (hours.size() > 0)
    {
      BusinessHour aHour = hours.get(hours.size() - 1);
      aHour.delete();
      hours.remove(aHour);
    }
    
    while (employeeSchedules.size() > 0)
    {
      Schedule aEmployeeSchedule = employeeSchedules.get(employeeSchedules.size() - 1);
      aEmployeeSchedule.delete();
      employeeSchedules.remove(aEmployeeSchedule);
    }
    
    while (terminals.size() > 0)
    {
      Terminal aTerminal = terminals.get(terminals.size() - 1);
      aTerminal.delete();
      terminals.remove(aTerminal);
    }
    
    while (salesReports.size() > 0)
    {
      Report aSalesReport = salesReports.get(salesReports.size() - 1);
      aSalesReport.delete();
      salesReports.remove(aSalesReport);
    }
    
    Owner existingOwner = owner;
    owner = null;
    if (existingOwner != null)
    {
      existingOwner.delete();
    }
    while (cashiers.size() > 0)
    {
      Cashier aCashier = cashiers.get(cashiers.size() - 1);
      aCashier.delete();
      cashiers.remove(aCashier);
    }
    
    while (deliverypersons.size() > 0)
    {
      DeliveryPerson aDeliveryperson = deliverypersons.get(deliverypersons.size() - 1);
      aDeliveryperson.delete();
      deliverypersons.remove(aDeliveryperson);
    }
    
    while (clerks.size() > 0)
    {
      Clerk aClerk = clerks.get(clerks.size() - 1);
      aClerk.delete();
      clerks.remove(aClerk);
    }
    
    while (customers.size() > 0)
    {
      Customer aCustomer = customers.get(customers.size() - 1);
      aCustomer.delete();
      customers.remove(aCustomer);
    }
    
    while (items.size() > 0)
    {
      Item aItem = items.get(items.size() - 1);
      aItem.delete();
      items.remove(aItem);
    }
    
    while (orders.size() > 0)
    {
      Order aOrder = orders.get(orders.size() - 1);
      aOrder.delete();
      orders.remove(aOrder);
    }
    
    while (timeSlots.size() > 0)
    {
      TimeSlot aTimeSlot = timeSlots.get(timeSlots.size() - 1);
      aTimeSlot.delete();
      timeSlots.remove(aTimeSlot);
    }
    
    while (carts.size() > 0)
    {
      Cart aCart = carts.get(carts.size() - 1);
      aCart.delete();
      carts.remove(aCart);
    }
    
  }


  public String toString()
  {
    return super.toString() + "["+
            "gsssID" + ":" + getGsssID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "store = "+(getStore()!=null?Integer.toHexString(System.identityHashCode(getStore())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "owner = "+(getOwner()!=null?Integer.toHexString(System.identityHashCode(getOwner())):"null");
  }
}