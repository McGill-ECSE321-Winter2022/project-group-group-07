package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;

public class PerishableItemDto extends ItemDto {

	/**
	 * @param itemID
	 * @param productName
	 * @param price
	 * @param availableOnline
	 * @param numInStock
	 * @param pointPerItem
	 * @param category
	 */
	public PerishableItemDto(Long itemID, String productName, Float price, Boolean availableOnline, Integer numInStock,
			Integer pointPerItem, String category) {
		super(itemID, productName, price, availableOnline, numInStock, pointPerItem, category);
	}

	/**
	 * @param itemID
	 * @param productName
	 * @param price
	 * @param availableOnline
	 * @param numInStock
	 * @param pointPerItem
	 * @param imageLink
	 * @param category
	 */
	public PerishableItemDto(Long itemID, String productName, Float price, Boolean availableOnline, Integer numInStock,
			Integer pointPerItem, String imageLink, String category) {
		super(itemID, productName, price, availableOnline, numInStock, pointPerItem, imageLink, category);
	}

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
	public PerishableItemDto(Long itemID, String productName, Float price, Boolean availableOnline,
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
	public PerishableItemDto(Long itemID, String productName, Float price, Boolean availableOnline,
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
