package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.List;

public abstract class OrderDto {

	private Integer orderID;
	private Float totalValue;
	private Date date;
	private Time purchaseTime;

	private AccountDto account;
	private List<ItemDto> items;

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param account
	 * @param items
	 */
	public OrderDto(Integer orderID, Float totalValue, Date date, Time purchaseTime, AccountDto account, List<ItemDto> items) {
		this.orderID = orderID;
		this.totalValue = totalValue;
		this.date = date;
		this.purchaseTime = purchaseTime;
		this.account = account;
		this.items = items;
	}

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param account
	 */
	@SuppressWarnings("unchecked")
	public OrderDto(Integer orderID, Float totalValue, Date date, Time purchaseTime, AccountDto account) {
		this (orderID, totalValue,date,  purchaseTime, account, Collections.EMPTY_LIST);
	}

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param items
	 */
	public OrderDto(Integer orderID, Float totalValue, Date date, Time purchaseTime, List<ItemDto> items) {
		this.orderID = orderID;
		this.totalValue = totalValue;
		this.date = date;
		this.purchaseTime = purchaseTime;
		this.items = items;
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
	 * @return the orderID
	 */
	public Integer getOrderID() {
		return orderID;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the purchaseTime
	 */
	public Time getPurchaseTime() {
		return purchaseTime;
	}
	
	

}
