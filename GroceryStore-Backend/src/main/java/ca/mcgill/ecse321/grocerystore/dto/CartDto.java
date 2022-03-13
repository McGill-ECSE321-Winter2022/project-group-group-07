package ca.mcgill.ecse321.grocerystore.dto;

import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.OrderType;

public class CartDto {

	// attribute fields
	private OrderType orderType;
	private Float totalValue;
	private Integer numOfItems;

	// association fields
	private List<ItemDto> items;
	
	/**
	 * @param orderType
	 * @param totalValue
	 * @param numOfItems
	 * @param items
	 * @param timeSlot
	 * @param account
	 */
	public CartDto(OrderType orderType, Float totalValue, Integer numOfItems, List<ItemDto> items, TimeSlotDto timeSlot,
			AccountDto account) {
		super();
		this.orderType = orderType;
		this.totalValue = totalValue;
		this.numOfItems = numOfItems;
		this.items = items;
		this.timeSlot = timeSlot;
		this.account = account;
	}
	
	
	/**
	 * @param orderType
	 * @param totalValue
	 * @param numOfItems
	 * @param items
	 * @param account
	 */
	public CartDto(OrderType orderType, Float totalValue, Integer numOfItems, List<ItemDto> items, AccountDto account) {
		super();
		this.orderType = orderType;
		this.totalValue = totalValue;
		this.numOfItems = numOfItems;
		this.items = items;
		this.account = account;
	}


	/**
	 * @param orderType
	 * @param totalValue
	 * @param numOfItems
	 * @param account
	 */
	public CartDto(OrderType orderType, Float totalValue, Integer numOfItems, AccountDto account) {
		super();
		this.orderType = orderType;
		this.totalValue = totalValue;
		this.numOfItems = numOfItems;
		this.account = account;
	}


	/**
	 * @param orderType
	 * @param totalValue
	 * @param numOfItems
	 * @param timeSlot
	 * @param account
	 */
	@SuppressWarnings("unchecked")
	public CartDto(OrderType orderType, Float totalValue, Integer numOfItems, TimeSlotDto timeSlot,
			AccountDto account) {
		
		this(orderType,totalValue,numOfItems,Collections.EMPTY_LIST,timeSlot,account);
	}


	/**
	 * @return the orderType
	 */
	public OrderType getOrderType() {
		return orderType;
	}
	/**
	 * @param orderType the orderType to set
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	/**
	 * @return the totalValue
	 */
	public Float getTotalValue() {
		return totalValue;
	}
	/**
	 * @param totalValue the totalValue to set
	 */
	public void setTotalValue(Float totalValue) {
		this.totalValue = totalValue;
	}
	/**
	 * @return the numOfItems
	 */
	public Integer getNumOfItems() {
		return numOfItems;
	}
	/**
	 * @param numOfItems the numOfItems to set
	 */
	public void setNumOfItems(Integer numOfItems) {
		this.numOfItems = numOfItems;
	}
	/**
	 * @return the items
	 */
	public List<ItemDto> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<ItemDto> items) {
		this.items = items;
	}
	/**
	 * @return the timeSlot
	 */
	public TimeSlotDto getTimeSlot() {
		return timeSlot;
	}
	/**
	 * @param timeSlot the timeSlot to set
	 */
	public void setTimeSlot(TimeSlotDto timeSlot) {
		this.timeSlot = timeSlot;
	}
	/**
	 * @return the account
	 */
	public AccountDto getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(AccountDto account) {
		this.account = account;
	}
	private TimeSlotDto timeSlot;
	private AccountDto account;

	

}
