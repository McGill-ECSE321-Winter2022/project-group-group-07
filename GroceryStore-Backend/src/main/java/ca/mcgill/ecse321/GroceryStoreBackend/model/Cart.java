package ca.mcgill.ecse321.GroceryStoreBackend.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
public class Cart {

	private int cartID;
	private OrderType orderType;
	private float totalValue;
	private int numOfItems;

	public enum OrderType {
		Delivery, PickUp
	};

	public void setCartID(int aCartID) {
		this.cartID = aCartID;
	}

	public void setTotalValue(float aTotalValue) {
		this.totalValue = aTotalValue;
	}

	public void setNumOfItems(int aNumOfItems) {
		this.numOfItems = aNumOfItems;
	}

	public void setOrderType(OrderType aType) {
		orderType = aType;
	}

	private Set<Item> items;

	@OneToMany(cascade = { CascadeType.ALL })
	public Set<Item> getItems() {
		return this.items;
	}

	public void setItems(Set<Item> itemss) {
		this.items = itemss;
	}

	private TimeSlot timeSlot;

	@OneToOne(optional = false)
	public TimeSlot getTimeSlot() {
		return this.timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	private User user;

	@OneToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User users) {
		this.user = users;
	}

	@Id
	public int getCartID() {
		return cartID;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public float getTotalValue() {
		return totalValue;
	}

	public int getNumOfItems() {
		return numOfItems;
	}

}