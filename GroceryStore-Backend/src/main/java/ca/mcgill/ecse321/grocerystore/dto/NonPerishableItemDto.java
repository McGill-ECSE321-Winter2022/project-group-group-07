package ca.mcgill.ecse321.grocerystore.dto;

import java.sql.Date;

public class NonPerishableItemDto {
	
	private Integer itemID;
	private String productName;
	private Float price;
	private Boolean availableOnline;
	private Integer numInStock;
	private Integer pointPerItem;
	
	public NonPerishableItemDto() {
	}
	
	public NonPerishableItemDto(Integer id, String name, Float price, Boolean availableOnline, Integer inStock, Integer point) {
		this.itemID = id;
		this.productName = name;
		this.price = price;
		this.availableOnline = availableOnline;
		this.numInStock = inStock;
		this.pointPerItem = point;
	}
	
	public Integer getID() {
		return itemID;
	}
	
	public void setName(String newName) {
		this.productName = newName;
	}
	
	public String getName() {
		return productName;
	}
	
	public void setPrice(Float newPrice) {
		this.price = newPrice;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setAvailability(Boolean available) {
		this.availableOnline = available;
	}
	
	public Boolean getAvailability() {
		return availableOnline;
	}
	
	public void setStock(Integer stock) {
		this.numInStock = stock;
	}
	
	public Integer getStock() {
		return numInStock;
	}
	
	public void setPoints(Integer point) {
		this.pointPerItem = point;
	}
	
	public Integer getPoints() {
		return pointPerItem;
	}
}
