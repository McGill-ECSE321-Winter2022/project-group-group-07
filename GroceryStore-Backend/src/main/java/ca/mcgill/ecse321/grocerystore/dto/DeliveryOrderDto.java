package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import ca.mcgill.ecse321.grocerystore.model.TimeSlot;

public class DeliveryOrderDto extends OrderDto {

	private TimeSlot timeSlot;

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param account
	 * @param items
	 * @param timeSlot
	 */
	public DeliveryOrderDto(Integer orderID, Float totalValue, Date date, Time purchaseTime, AccountDto account,
			List<ItemDto> items, TimeSlot timeSlot) {
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
	public DeliveryOrderDto(Integer orderID, Float totalValue, Date date, Time purchaseTime, AccountDto account,
			TimeSlot timeSlot) {
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
	 */
	public DeliveryOrderDto(Integer orderID, Float totalValue, Date date, Time purchaseTime, List<ItemDto> items,
			TimeSlot timeSlot) {
		super(orderID, totalValue, date, purchaseTime, items);
		this.timeSlot = timeSlot;
	}

	/**
	 * @return the timeSlot
	 */
	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

}
