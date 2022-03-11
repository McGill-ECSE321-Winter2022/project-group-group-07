package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.Item;
import ca.mcgill.ecse321.grocerystore.model.TimeSlot;

public class DeliveryOrderDto {
	//attribute fields
		private Account account;
		private Integer orderID;
		private Float totalValue;
		private Date date;
		private Time purchaseTime;

		//association fields
		private Set<Item> items;
		private TimeSlot timeSlot;
		/**
		 * @param account
		 * @param orderID
		 * @param totalValue
		 * @param date
		 * @param purchaseTime
		 * @param items
		 * @param timeSlot
		 */
		public DeliveryOrderDto(Account account, Integer orderID, Float totalValue, Date date, Time purchaseTime,
				Set<Item> items, TimeSlot timeSlot) {
			this.account = account;
			this.orderID = orderID;
			this.totalValue = totalValue;
			this.date = date;
			this.purchaseTime = purchaseTime;
			this.items = items;
			this.timeSlot = timeSlot;
		}
		
		/**
		 * @param account
		 * @param orderID
		 * @param date
		 * @param purchaseTime
		 * @param timeSlot
		 */
		public DeliveryOrderDto(Account account, Integer orderID, Date date, Time purchaseTime, TimeSlot timeSlot) {
			this.account = account;
			this.orderID = orderID;
			this.date = date;
			this.purchaseTime = purchaseTime;
			this.timeSlot = timeSlot;
		}

		/**
		 * 
		 */
		public DeliveryOrderDto() {
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
		 * @return the orderID
		 */
		public Integer getOrderID() {
			return orderID;
		}
//		/**
//		 * @param orderID the orderID to set
//		 */
//		public void setOrderID(Integer orderID) {
//			this.orderID = orderID;
//		}
		/**
		 * @return the totalValue
		 */
		public Float getTotalValue() {
			return totalValue;
		}
//		/**
//		 * @param totalValue the totalValue to set
//		 */
//		public void setTotalValue(Float totalValue) {
//			this.totalValue = totalValue;
//		}
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
//		/**
//		 * @param items the items to set
//		 */
//		public void setItems(Set<Item> items) {
//			this.items = items;
//		}
		/**
		 * @return the timeSlot
		 */
		public TimeSlot getTimeSlot() {
			return timeSlot;
		}
//		/**
//		 * @param timeSlot the timeSlot to set
//		 */
//		public void setTimeSlot(TimeSlot timeSlot) {
//			this.timeSlot = timeSlot;
//		}
		
		
		
}
