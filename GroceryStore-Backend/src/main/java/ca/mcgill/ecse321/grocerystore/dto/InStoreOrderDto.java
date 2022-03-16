package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


public class InStoreOrderDto extends OrderDto {

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param account
	 * @param items
	 */
	public InStoreOrderDto(Long orderID, Float totalValue, Date date, Time purchaseTime, AccountDto account,
			List<ItemDto> items) {
		super(orderID, totalValue, date, purchaseTime, account, items);

	}

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param items
	 */
	public InStoreOrderDto(Long orderID, Float totalValue, Date date, Time purchaseTime, List<ItemDto> items) {
		super(orderID, totalValue, date, purchaseTime, items);
	}

	/**
	 * @param orderID
	 * @param totalValue
	 * @param date
	 * @param purchaseTime
	 * @param account
	 */
	public InStoreOrderDto(Long orderID, Float totalValue, Date date, Time purchaseTime, AccountDto account) {
		super(orderID, totalValue, date, purchaseTime, account);

	}

}
