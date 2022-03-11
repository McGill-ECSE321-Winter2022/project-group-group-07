package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.Item;

public class InStoreOrderDto {
	//attribute fields
		private Account account;
		private Integer orderID;
		private Float totalValue;
		private Date date;
		private Time purchaseTime;

		//association fields
		private Set<Item> items;

		/**
		 * @param account
		 * @param orderID
		 * @param totalValue
		 * @param date
		 * @param purchaseTime
		 * @param items
		 */
		public InStoreOrderDto(Account account, Integer orderID, Float totalValue, Date date, Time purchaseTime,
				Set<Item> items) {
			this.account = account;
			this.orderID = orderID;
			this.totalValue = totalValue;
			this.date = date;
			this.purchaseTime = purchaseTime;
			this.items = items;
		}

		/**
		 * @param orderID
		 * @param totalValue
		 * @param date
		 * @param purchaseTime
		 * @param items
		 */
		public InStoreOrderDto(Integer orderID, Float totalValue, Date date, Time purchaseTime, Set<Item> items) {
			this.orderID = orderID;
			this.totalValue = totalValue;
			this.date = date;
			this.purchaseTime = purchaseTime;
			this.items = items;
		}
		


		/**
		 * @param account
		 * @param orderID
		 * @param date
		 * @param purchaseTime
		 */
		public InStoreOrderDto(Account account, Integer orderID, Date date, Time purchaseTime) {
			this.account = account;
			this.orderID = orderID;
			this.date = date;
			this.purchaseTime = purchaseTime;
		}
		
		
		/**
		 * @param orderID
		 * @param date
		 * @param purchaseTime
		 */
		public InStoreOrderDto(Integer orderID, Date date, Time purchaseTime) {
			this.orderID = orderID;
			this.date = date;
			this.purchaseTime = purchaseTime;
		}

		/**
		 * 
		 */
		public InStoreOrderDto() {
		}

		/**
		 * @return the account
		 */
		public Account getAccount() {
			return account;
		}

//		/**
//		 * @param account the account to set
//		 */
//		public void setAccount(Account account) {
//			this.account = account;
//		}

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
		 * @return the date
		 */
		public Date getDate() {
			return date;
		}

//		/**
//		 * @param date the date to set
//		 */
//		public void setDate(Date date) {
//			this.date = date;
//		}

		/**
		 * @return the purchaseTime
		 */
		public Time getPurchaseTime() {
			return purchaseTime;
		}

//		/**
//		 * @param purchaseTime the purchaseTime to set
//		 */
//		public void setPurchaseTime(Time purchaseTime) {
//			this.purchaseTime = purchaseTime;
//		}

		/**
		 * @return the items
		 */
		public Set<Item> getItems() {
			return items;
		}

		/**
		 * @param items the items to set
		 */
		public void setItems(Set<Item> items) {
			this.items = items;
		}

		/**
		 * @return the orderID
		 */
		public Integer getOrderID() {
			return orderID;
		}
		

}
