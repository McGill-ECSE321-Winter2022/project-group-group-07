package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import ca.mcgill.ecse321.grocerystore.model.GroceryStoreSoftwareSystem.DeliveryOrderStatus;

public class DeliveryOrderDto extends OrderDto {

	private TimeSlotDto timeSlot;
	private DeliveryOrderStatus status;

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param account
	 * @param items
	 * @param timeSlot
	 */
	public DeliveryOrderDto(Long orderID, Float totalValue, Date date, Time purchaseTime, AccountDto account,
			List<ItemDto> items, TimeSlotDto timeSlot) {
		super(orderID, totalValue, date, purchaseTime, account, items);
		this.timeSlot = timeSlot;
	}

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param account
	 * @param timeSlot
	 */
	public DeliveryOrderDto(Long orderID, Float totalValue, Date date, Time purchaseTime, AccountDto account,
			TimeSlotDto timeSlot) {
		super(orderID, totalValue, date, purchaseTime, account);
		this.timeSlot = timeSlot;
	}

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param items
	 * @param timeSlot
	 * @param status
	 */
	public DeliveryOrderDto(Long orderID, Float totalValue, Date date, Time purchaseTime, List<ItemDto> items,
			TimeSlotDto timeSlot, DeliveryOrderStatus status) {
		super(orderID, totalValue, date, purchaseTime, items);
		this.timeSlot = timeSlot;
		this.status = status;
	}

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param account
	 * @param timeSlot
	 * @param status
	 */
	public DeliveryOrderDto(Long orderID, Float totalValue, Date date, Time purchaseTime, AccountDto account,
			TimeSlotDto timeSlot, DeliveryOrderStatus status) {
		super(orderID, totalValue, date, purchaseTime, account);
		this.timeSlot = timeSlot;
		this.status = status;
	}

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param account
	 * @param items
	 * @param timeSlot
	 * @param status
	 */
	public DeliveryOrderDto(Long orderID, Float totalValue, Date date, Time purchaseTime, AccountDto account,
			List<ItemDto> items, TimeSlotDto timeSlot, DeliveryOrderStatus status) {
		super(orderID, totalValue, date, purchaseTime, account, items);
		this.timeSlot = timeSlot;
		this.status = status;
	}

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param items
	 * @param timeSlot
	 */
	public DeliveryOrderDto(Long orderID, Float totalValue, Date date, Time purchaseTime, List<ItemDto> items,
			TimeSlotDto timeSlot) {
		super(orderID, totalValue, date, purchaseTime, items);
		this.timeSlot = timeSlot;
	}

	/**
	 * @return the timeSlot
	 */
	public TimeSlotDto getTimeSlot() {
		return timeSlot;
	}

	/**
	 * @return the status
	 */
	public DeliveryOrderStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(DeliveryOrderStatus status) {
		this.status = status;
	}

}