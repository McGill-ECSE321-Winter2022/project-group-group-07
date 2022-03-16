package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;

public class PerishableItemDto extends ItemDto {

	private Date perishableDate;

	/**
	 * @param itemID
	 * @param productName
	 * @param price
	 * @param availableOnline
	 * @param numInStock
	 * @param pointPerItem
	 * @param perishableDate
	 */
	public PerishableItemDto(Integer itemID, String productName, Float price, Boolean availableOnline,
			Integer numInStock, Integer pointPerItem, Date perishableDate) {
		super(itemID, productName, price, availableOnline, numInStock, pointPerItem);
		this.perishableDate = perishableDate;
	}

	/**
	 * @param itemID
	 * @param productName
	 * @param price
	 * @param availableOnline
	 * @param numInStock
	 * @param pointPerItem
	 */
	public PerishableItemDto(Integer itemID, String productName, Float price, Boolean availableOnline,
			Integer numInStock, Integer pointPerItem) {
		super(itemID, productName, price, availableOnline, numInStock, pointPerItem);
	}

	/**
	 * @return the perishableDate
	 */
	public Date getPerishableDate() {
		return perishableDate;
	}

	/**
	 * @param perishableDate the perishableDate to set
	 */
	public void setPerishableDate(Date perishableDate) {
		this.perishableDate = perishableDate;
	}

}
