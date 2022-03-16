package ca.mcgill.ecse321.grocerystore.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
public class GroceryStoreSoftwareSystem {

	// enum classes
	public enum DayOfWeek {
		Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
	}

	public enum OrderType {
		InStore, Delivery, PickUp
	}

	public enum DeliveryOrderStatus {
		Pending, OutForDelivery, Delivered, Canceled
	}

	public enum ItemCategory {
		BackedGoods, FruitsAndVegetables, Toiletries, Pantry, MeatsAndFish 
	}

	public enum PickUpOrderStatus {
		Pending, Ready, PickedUp, Canceled
	}

	// attribute fields
	private Long systemID;

	// association fields
	private Set<Account> accounts;
	private Store store;
	private Set<BusinessHour> hours;
	private Set<Schedule> employeeSchedules;
	private Set<Terminal> terminals;
	private Set<Report> salesReports;
	private Owner owner;
	private Set<Cashier> cashiers;
	private Set<DeliveryPerson> deliveryPersons;
	private Set<Clerk> clerks;
	private Set<Customer> customers;
	private Set<Item> items;
	private Set<Order> orders;
	private Set<TimeSlot> timeSlots;
	private Set<Cart> carts;
	private Set<Address> addresses;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getSystemID() {
		return systemID;
	}

	public void setSystemID(Long ID) {
		systemID = ID;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Address> getaddresses() {
		return addresses;
	}

	public void setaddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}

	@OneToOne(optional = false)
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<BusinessHour> getHours() {
		return hours;
	}

	public void setHours(Set<BusinessHour> hours) {
		this.hours = hours;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Schedule> getEmployeeSchedules() {
		return employeeSchedules;
	}

	public void setEmployeeSchedules(Set<Schedule> employeeSchedules) {
		this.employeeSchedules = employeeSchedules;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Terminal> getTerminals() {
		return terminals;
	}

	public void setTerminals(Set<Terminal> terminals) {
		this.terminals = terminals;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Report> getSalesReports() {
		return salesReports;
	}

	public void setSalesReports(Set<Report> salesReports) {
		this.salesReports = salesReports;
	}

	@OneToOne(optional = false)
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Cashier> getCashiers() {
		return cashiers;
	}

	public void setCashiers(Set<Cashier> cashiers) {
		this.cashiers = cashiers;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<DeliveryPerson> getDeliveryPersons() {
		return deliveryPersons;
	}

	public void setDeliveryPersons(Set<DeliveryPerson> deliveryPersons) {
		this.deliveryPersons = deliveryPersons;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Clerk> getClerks() {
		return clerks;
	}

	public void setClerks(Set<Clerk> clerks) {
		this.clerks = clerks;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(Set<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

}