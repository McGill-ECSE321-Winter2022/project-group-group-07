package ca.mcgill.ecse321.grocerystore.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
public class Cart {

	private Integer cartID;
	private OrderType orderType;
	private Float totalValue;
	private Integer numOfItems;
	
	private Set<Item> items;
	private TimeSlot timeSlot;
	private Account account;

	public enum OrderType {
		Delivery, PickUp
	};

	public void setCartID(Integer aCartID) {
		this.cartID = aCartID;
	}

	public void setTotalValue(Float aTotalValue) {
		this.totalValue = aTotalValue;
	}

	public void setNumOfItems(Integer aNumOfItems) {
		this.numOfItems = aNumOfItems;
	}

	public void setOrderType(OrderType aType) {
		orderType = aType;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> itemss) {
		this.items = itemss;
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

	public void setaccount(Account accounts) {
		this.account = accounts;
	}

	@Id
	public Integer getCartID() {
		return cartID;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public Float getTotalValue() {
		return totalValue;
	}

	public Integer getNumOfItems() {
		return numOfItems;
	}

}