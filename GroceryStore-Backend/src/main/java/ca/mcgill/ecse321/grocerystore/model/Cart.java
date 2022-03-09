package ca.mcgill.ecse321.grocerystore.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.OrderType;

@Entity
public class Cart {

	// attribute fields
	private Integer cartID;
	private OrderType orderType;
	private Float totalValue;
	private Integer numOfItems;

	// association fields
	private Set<Item> items;
	private TimeSlot timeSlot;
	private Account account;

	
	// getters and setters
	@Id
	public Integer getCartID() {
		return cartID;
	}

	public void setCartID(Integer aCartID) {
		this.cartID = aCartID;
	}

	public void setTotalValue(Float aTotalValue) {
		this.totalValue = aTotalValue;
	}

	public Float getTotalValue() {
		return totalValue;
	}

	public void setNumOfItems(Integer aNumOfItems) {
		this.numOfItems = aNumOfItems;
	}

	public Integer getNumOfItems() {
		return numOfItems;
	}

	public void setOrderType(OrderType aType) {
		orderType = aType;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@OneToOne(optional = false)
	public TimeSlot getTimeSlot() {
		return this.timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	@OneToOne(optional = true)
	public Account getaccount() {

		return this.account;
	}

	public void setAccount(Account accounts) {
		this.account = accounts;
	}

}