package ca.mcgill.ecse321.grocerystore.dto;


public class NonPerishableItemDto extends ItemDto {

	public NonPerishableItemDto(Long itemID, String productName, Float price, Boolean availableOnline,
			Integer numInStock, Integer pointPerItem) {
		super(itemID, productName, price, availableOnline, numInStock, pointPerItem);
	}
	
	public NonPerishableItemDto(Long itemID, String productName, Float price, Boolean availableOnline,
			Integer numInStock, Integer pointPerItem, String imageLink, String category) {
		super(itemID, productName, price, availableOnline, numInStock, pointPerItem, imageLink, category);
	}
	
	public NonPerishableItemDto(Long itemID, String productName, Float price, Boolean availableOnline,
			Integer numInStock, Integer pointPerItem, String category) {
		super(itemID, productName, price, availableOnline, numInStock, pointPerItem, category);
	}
	
}
