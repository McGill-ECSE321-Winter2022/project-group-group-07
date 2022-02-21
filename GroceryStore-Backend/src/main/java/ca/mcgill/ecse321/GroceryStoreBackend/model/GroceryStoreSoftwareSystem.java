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
  public enum DayOfWeek { Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday }
  public enum OrderType { InStore, Delivery, PickUp }

  //GroceryStoreSoftwareSystem Attributes
  private int systemID;

  //GroceryStoreSoftwareSystem Associations
  private Store store;
  private Set<BusinessHour> hours;
  private Set<Schedule> employeeSchedules;
  private Set<Terminal> terminals;
  private Set<Report> salesReports;
  private Owner owner;
  private Set<Cashier> cashiers;
  private Set<DeliveryPerson> deliverypersons;
  private Set<Clerk> clerks;
  private Set<Customer> customers;
  private Set<Item> items;
  private Set<Order> orders;
  private Set<TimeSlot> timeSlots;
  private Set<Cart> carts;

  
  public void setSystemID(int ID)
  {
    systemID = ID;
  }
  @Id
  public int getSystemID()
  {
    return systemID;
  }

  @OneToOne
  public Store getStore()
  {
    return store;
  }
  @OneToMany
  public Set<BusinessHour> getHours()
  {
    return hours;
  }
  @OneToMany
  public Set<Schedule> getEmployeeSchedules()
  {
    return employeeSchedules;
  }
  @OneToMany
  public Set<Terminal> getTerminals()
  {
    return terminals;
  }

  @OneToMany
  public Set<Report> getSalesReports()
  {
    return salesReports;
  }

  @OneToOne
  public Owner getOwner()
  {
    return owner;
  }
  
  @OneToMany
  public Set<Cashier> getCashiers()
  {
    return cashiers;
  }

  @OneToMany
  public Set<DeliveryPerson> getDeliverypersons()
  {
    return deliverypersons;
  }

  @OneToMany
  public Set<Clerk> getClerks()
  {
    return clerks;
  }

  @OneToMany
  public Set<Customer> getCustomers()
  {
    return customers;
  }

  @OneToMany
  public Set<Item> getItems()
  {
    return items;
  }

  @OneToMany
  public Set<Order> getOrders()
  {
    return orders;
  }

  @OneToMany
  public Set<TimeSlot> getTimeSlots()
  {
    return timeSlots;
  }

  @OneToMany
  public Set<Cart> getCarts()
  {
    return carts;
  }

  public boolean setStore(Store store){
    if(store != null){
      this.store = store;
      return true;
    }
    return false;
  }

  public void setHours(Set<BusinessHour> hours){
    this.hours = hours;
  }
  
  public void setEmployeeSchedules(Set<Schedule> employeeSchedules){
    this.employeeSchedules = employeeSchedules;
  }
  
  public void setTerminals(Set<Terminal> terminals){
    this.terminals = terminals;
  }

  public void setSalesReports(Set<Report> salesReports){
    this.salesReports = salesReports;
  }

  public boolean setOwner(Owner owner){
    if(owner != null){
      this.owner = owner;
      return true;
    }
    return false;
  }
  
  public void setCashiers(Set<Cashier> cashiers){
    this.cashiers = cashiers;
  }

  public void setDeliveryPersons(Set<DeliveryPerson> deliveryPersons){
    this.deliverypersons = deliveryPersons;
  }

  public void setClerks(Set<Clerk> clerks){
    this.clerks = clerks;
  }
  
  public void setCustomers(Set<Customer> customers){
    this.customers = customers;
  }

  public void setItems(Set<Item> items){
    this.items = items;
  }

  public void setOrders(Set<Order> orders){
    this.orders = orders;
  }

  public void setTimeSlots(Set<TimeSlot> timeSlots){
    this.timeSlots = timeSlots;
  }

  public void setCarts(Set<Cart> carts){
    this.carts = carts;
  }

}