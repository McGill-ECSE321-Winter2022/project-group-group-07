package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import ca.mcgill.ecse321.grocerystore.model.Account;
import ca.mcgill.ecse321.grocerystore.model.Item;
import ca.mcgill.ecse321.grocerystore.model.TimeSlot;
import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.OrderType;

public class CartDto {
	// attribute fields
		private Integer cartID;
		private OrderType orderType;
		private Float totalValue;
		private Integer numOfItems;

		// association fields
		private Set<Item> items;
		private TimeSlot timeSlot;
		private Account account;
		
		/**
		 * @param cartID
		 * @param orderType
		 * @param totalValue
		 * @param numOfItems
		 * @param items
		 * @param timeSlot
		 * @param account
		 */
		public CartDto(Integer cartID, OrderType orderType, Float totalValue, Integer numOfItems, Set<Item> items,
				TimeSlot timeSlot, Account account) {
			this.cartID = cartID;
			this.orderType = orderType;
			this.totalValue = totalValue;
			this.numOfItems = numOfItems;
			this.items = items;
			this.timeSlot = timeSlot;
			this.account = account;
		}
		
		
		/**
		 * @param cartID
		 * @param orderType
		 * @param timeSlot
		 * @param account
		 */
		public CartDto(Integer cartID, OrderType orderType, TimeSlot timeSlot, Account account) {
			this.cartID = cartID;
			this.orderType = orderType;
			this.timeSlot = timeSlot;
			this.account = account;
		}


		/**
		 * @return the cartID
		 */
		public Integer getCartID() {
			return cartID;
		}
//		/**
//		 * @param cartID the cartID to set
//		 */
//		public void setCartID(Integer cartID) {
//			this.cartID = cartID;
//		}
		/**
		 * @return the orderType
		 */
		public OrderType getOrderType() {
			return orderType;
		}
//		/**
//		 * @param orderType the orderType to set
//		 */
//		public void setOrderType(OrderType orderType) {
//			this.orderType = orderType;
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
		 * @return the numOfItems
		 */
		public Integer getNumOfItems() {
			return numOfItems;
		}
	
//		/**
//		 * @param numOfItems the numOfItems to set
//		 */
//		public void setNumOfItems(Integer numOfItems) {
//			this.numOfItems = numOfItems;
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
		
		
		
}
