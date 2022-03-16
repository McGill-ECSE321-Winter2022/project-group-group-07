package ca.mcgill.ecse321.grocerystore.dto;


public class NonPerishableItemDto extends ItemDto {

	public NonPerishableItemDto(Integer itemID, String productName, Float price, Boolean availableOnline,
			Integer numInStock, Integer pointPerItem) {
		super(itemID, productName, price, availableOnline, numInStock, pointPerItem);
	}
	
}
